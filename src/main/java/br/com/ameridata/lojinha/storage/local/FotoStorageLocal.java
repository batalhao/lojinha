package br.com.ameridata.lojinha.storage.local;

import static com.google.common.io.Files.getFileExtension;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.hash.Hashing;

import br.com.ameridata.lojinha.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger LOG = LoggerFactory.getLogger(FotoStorageLocal.class);

	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this(FileSystems.getDefault().getPath("C:\\Lojinha", "LJfotos"));
	}

	public FotoStorageLocal(Path path) {
		this.local = path;
		this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "LJtemp");

		criarPastas();
	}

	@Override
	public String saveTemp(MultipartFile[] files) {
		String novoNome = null;

		if (files != null && files.length > 0) {
			MultipartFile file = files[0];

			novoNome = renomearArquivo(file.getOriginalFilename());

			String localArquivo = this.localTemporario.toAbsolutePath().toString()
					+ FileSystems.getDefault().getSeparator() + novoNome;

			try {
				file.transferTo(new File(localArquivo));
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(String.format("Erro ao salvar o arquivo na pasta: %s", localArquivo), e);
			}
		}

		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException(String.format("Erro ao recuperar a foto temporária: %s",
					this.localTemporario.resolve(nome).toString()), e);
		}
	}

	@Override
	public byte[] recuperarFoto(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException(
					String.format("Erro ao recuperar a foto: %s", this.local.resolve(nome).toString()), e);
		}
	}

	@Override
	public void save(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException(String.format("Erro ao mover a foto de: %s, para: %s",
					this.localTemporario.resolve(foto), this.local.resolve(foto)), e);
		}

		Instant instant = Instant.now();
		FileTime fileTime = FileTime.from(instant);

		try {
			Files.setLastModifiedTime(this.local.resolve(foto), fileTime);
		} catch (IOException e) {
			throw new RuntimeException(
					String.format("Erro ao atualizar a data/hora da foto: %s", this.local.resolve(foto).toString()), e);
		}

		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(100, 100).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException(
					String.format("Erro ao criar o Thumbnail da foto: %s", this.local.resolve(foto).toString()), e);
		}
	}

	private void criarPastas() {
		String absolutePath = "";

		try {
			Files.createDirectories(this.local);
			absolutePath = this.local.toAbsolutePath().toString();

			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Diretório criado: %s", absolutePath));
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format("Erro ao criar o diretório: %s", absolutePath), e);
		}

		try {
			Files.createDirectories(this.localTemporario);
			absolutePath = this.localTemporario.toAbsolutePath().toString();

			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Diretório temporário criado: %s", absolutePath));
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format("Erro ao criar o diretório: %s", absolutePath), e);
		}
	}

	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		String extensaoArquivo = getFileExtension(nomeOriginal);
		novoNome = Hashing.sha256().hashString(novoNome, StandardCharsets.UTF_8).toString() + "." + extensaoArquivo;

		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Nome original do arquivo: %s", nomeOriginal));
			LOG.debug(String.format("Nome final do arquivo: %s", novoNome));
		}

		return novoNome;
	}

}

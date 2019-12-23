package br.com.ameridata.lojinha.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.ameridata.lojinha.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> deferredResult;
	private FotoStorage fotoStorage;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> deferredResult, FotoStorage fotoStorage) {
		this.files = files;
		this.deferredResult = deferredResult;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
		/* String nomeFoto = files[0].getOriginalFilename(); */

		String nomeFoto = this.fotoStorage.saveTemp(files);
		String contentType = files[0].getContentType();

		deferredResult.setResult(new FotoDTO(nomeFoto, contentType));
	}

}

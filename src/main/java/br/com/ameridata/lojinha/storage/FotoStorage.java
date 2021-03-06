package br.com.ameridata.lojinha.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String saveTemp(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);

	public byte[] recuperarFoto(String nome);

	public void save(String foto);

}

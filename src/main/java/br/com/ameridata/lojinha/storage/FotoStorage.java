package br.com.ameridata.lojinha.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String saveTemp(MultipartFile[] files);

}

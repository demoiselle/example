package org.demoiselle.estacionamento.view;


import javax.enterprise.context.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

@ViewController
@SessionScoped
public class ClientePhotoUploader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UploadedFile uploadedFile = null;
	
	private StreamedContent photoView = null;
		
	public StreamedContent getPhotoView() {
		return photoView;
	}

	public void setPhotoView(StreamedContent photoView) {
		this.photoView = photoView;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile varUploadedFile = event.getFile();
		this.setUploadedFile(varUploadedFile);
		this.setPhotoView(new DefaultStreamedContent(new ByteArrayInputStream(varUploadedFile.getContents())));
	}
	

}

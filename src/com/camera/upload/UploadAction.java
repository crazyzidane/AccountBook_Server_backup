package com.camera.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.List;

/**
 * Servlet implementation class UploadAction
 */
@WebServlet("/UploadAction")
public class UploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		System.out.println("paht" +path);
		DiskFileItemFactory factory =new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//设置上传文件的大小,4M
		fileUpload.setSizeMax(4*1024*1024);
		fileUpload.setFileSizeMax(2*1024*1024);
		List<FileItem> list = null;
		try {
			list = fileUpload.parseRequest(request);
			for (FileItem item:list) {
				//一种是表单数据，另一种是图片数据
				if(!item.isFormField()){
					String img = item.getName();
					String file_upload_path = request.getRealPath("/upload") + "/" + img;
					System.out.println("图片上传的路径：" + file_upload_path);
					item.write(new File(file_upload_path));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

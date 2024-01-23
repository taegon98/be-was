package response;

import util.StatusCode;

import java.io.*;

import static util.MimeType.getMimeType;

public class HttpResponse {
    //todo: 일급 컬렉션으로 변경
    private final StatusLine statusLine;
    private final String contentType;
    private final String redirectUri;
    private final byte[] body;

    public HttpResponse(StatusCode statusCode, String contentType, String redirectUri) {
        this.statusLine = new StatusLine(statusCode);
        this.contentType = contentType;
        this.redirectUri = redirectUri;
        this.body = null;
    }

    public HttpResponse(StatusCode statusCode, String filePath) throws IOException {
        this.statusLine = new StatusLine(statusCode);
        this.contentType = getContentType(filePath);
        this.redirectUri = null;
        this.body = readFileInBytes(filePath);
    }

    public HttpResponse(StatusCode statusCode) {
        this.statusLine = new StatusLine(statusCode);
        this.contentType = null;
        this.redirectUri = null;
        this.body = "File Not Found".getBytes();
    }

    public Integer getBodyLength() {
        return body.length;
    }

    public String getContentType() {
        return contentType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public byte[] getBody() {
        return body;
    }

    public Integer getStatusCode() {
        return statusLine.getStatusCode();
    }

    private static byte[] readFileInBytes(String filePath) throws IOException { // 파일을 읽어서 byte[]로 반환
        File file = new File(filePath);

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] buffer = new byte[(int) file.length()];
        bis.read(buffer);

        return buffer;
    }

    private static String getContentType(String file) { // 파일의 확장자에 따라 Content-Type을 결정
        System.out.println(file);
        String extension = file.substring(file.lastIndexOf("."));
        return getMimeType(extension);
    }
}

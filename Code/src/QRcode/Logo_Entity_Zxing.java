package QRcode;
import java.io.Serializable;  
  
public class Logo_Entity_Zxing implements Serializable{  
  
    private static final long serialVersionUID = 1L;  
  
    /**  
     * ��ά������  
     */  
    private String contents;  
    /**  
     * ͼƬ�Ŀ��  
     */  
    private int width;  
    /**  
     * ͼƬ�ĸ߶�  
     */  
    private int height;  
    /**  
     * ����ͼƬ�ĵ�ַ��������ͼƬ���ƣ�  
     */  
    private String path;  
    /**  
     * logoͼ��ַ  
     */  
    private String logoPath;  
    /**  
     * ����ͼƬ�ĸ�ʽ  
     */  
    private String format;  
    /**  
     * ������  
     */  
    private Object errorCorrectionLevel;  
    /**  
     * �����ʽ  
     */  
    private String characterSet;  
    /**  
     * ��ά���Ե����  
     */  
    private int margin;  
    /**  
     * �Ƿ��м���ͼ  
     * @return  
     */  
    private boolean flag;  
      
    /**getter and setter*/  
      
    public String getContents() {  
        return contents;  
    }  
    public void setContents(String contents) {  
        this.contents = contents;  
    }  
    public int getWidth() {  
        return width;  
    }  
    public void setWidth(int width) {  
        this.width = width;  
    }  
    public int getHeight() {  
        return height;  
    }  
    public void setHeight(int height) {  
        this.height = height;  
    }  
    public String getPath() {  
        return path;  
    }  
    public void setPath(String path) {  
        this.path = path;  
    }  
    public String getFormat() {  
        return format;  
    }  
    public void setFormat(String format) {  
        this.format = format;  
    }  
    public Object getErrorCorrectionLevel() {  
        return errorCorrectionLevel;  
    }  
    public void setErrorCorrectionLevel(Object errorCorrectionLevel) {  
        this.errorCorrectionLevel = errorCorrectionLevel;  
    }  
    public String getCharacterSet() {  
        return characterSet;  
    }  
    public void setCharacterSet(String characterSet) {  
        this.characterSet = characterSet;  
    }  
    public int getMargin() {  
        return margin;  
    }  
    public void setMargin(int margin) {  
        this.margin = margin;  
    }  
    public boolean isFlag() {  
        return flag;  
    }  
    public void setFlag(boolean flag) {  
        this.flag = flag;  
    }  
    public String getLogoPath() {  
        return logoPath;  
    }  
    public void setLogoPath(String logoPath) {  
        this.logoPath = logoPath;  
    }   
}  
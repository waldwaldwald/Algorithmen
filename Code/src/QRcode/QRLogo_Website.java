package QRcode;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRLogo_Website {

	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;
	static String url = null;

	/**
	 * ����QRCode��ά��
	 */
	public void encode(Logo_Entity_Zxing zxing) {
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			/* ���þ�����(L 7%~M 15%~Q 25%~H 30%),������Խ�ߴ洢����ϢԽ�� */
			hints.put(EncodeHintType.ERROR_CORRECTION,
					zxing.getErrorCorrectionLevel());
			/* ���ñ����ʽ */
			hints.put(EncodeHintType.CHARACTER_SET, zxing.getCharacterSet());
			/* ���ñ�Ե�հ� */
			hints.put(EncodeHintType.MARGIN, zxing.getMargin());

			BitMatrix bitMatrix = new MultiFormatWriter().encode(
					zxing.getContents(), BarcodeFormat.QR_CODE,
					zxing.getWidth(), zxing.getHeight(), hints);

			File isFile = new File(zxing.getPath());
			if (!isFile.exists()) {
				isFile.mkdirs();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = sdf.format(new Date()) + "." + zxing.getFormat();
			url = zxing.getPath() + fileName;

			writeToFile(bitMatrix, zxing.getFormat(), new File(url),
					zxing.isFlag(), zxing.getLogoPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ɶ�ά��ͼƬ
	 * 
	 * @param bitMatrix
	 * @param format
	 *            ͼƬ��ʽ
	 * @param file
	 *            ���ɶ�ά��ͼƬλ��
	 * @param isLogo
	 *            �Ƿ�Ҫ��logoͼ
	 * @param logoPath
	 *            logoͼƬ��ַ
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix bitMatrix, String format,
			File file, boolean isLogo, String logoPath) throws IOException {
		BufferedImage bi = toBufferedImageContents(bitMatrix);
		if (isLogo) {
			int width_4 = bitMatrix.getWidth() / 3;
			int width_8 = bitMatrix.getWidth() / 3;
			int height_4 = bitMatrix.getHeight() / 3;
			int height_8 = bitMatrix.getHeight() / 3;

			// int width_4 = bitMatrix.getWidth() / 4;
			// int height_4 = bitMatrix.getHeight() / 4;
			// int width_8 = width_4 / 2;
			// int height_8 = height_4 / 2;
			System.out.println(width_4);

			System.out.println(width_8);

			System.out.println(height_4);

			System.out.println(height_8);
			/* ������ָ���������������ͼ�� */
			BufferedImage bi2 = bi.getSubimage(width_8, height_8, width_4,
					height_4);
			// BufferedImage bi2 = bi.getSubimage(width_4+width_8,
			// height_4+height_8, width_4,
			// height_4);
			
			/* ��ȡһ����ͼ���߱� */
			Graphics2D g2 = bi2.createGraphics();
			/* ��ȡlogoͼƬ��Ϣ */
			Image img = ImageIO.read(new File(logoPath));// ʵ����һ��Image����
			/* ��ǰͼƬ�Ŀ���� */
			int currentImgWidth = img.getWidth(null);
			int currentImgHeight = img.getHeight(null);
			/* ����ͼƬ�Ŀ���� */
			int resultImgWidth = 0;
			int resultImgHeight = 0;
			if (currentImgWidth != width_4) {
				resultImgWidth = width_4;
			}
			if (currentImgHeight != width_4) {
				resultImgHeight = width_4;
			}
			/* ����ͼƬ */
			g2.drawImage(img, 0, 0, resultImgWidth, resultImgHeight, null);
			g2.dispose();
			bi.flush();
		}
		ImageIO.write(bi, format, file);
	}

	/**
	 * ���ɶ�ά������
	 * 
	 * @param bitMatrix
	 * @return
	 */
	public static BufferedImage toBufferedImageContents(BitMatrix bitMatrix) {
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * ������ά��
	 * 
	 * @param path
	 *            ͼƬ�ľ���·��
	 */
	public void decode(String path) {
		try {
			if (path == null || path.equals("")) {
				System.out.println("�ļ�·������Ϊ��!");
			}
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			/* �ж��Ƿ���ͼƬ */
			if (image == null) {
				System.out.println("Could not decode image");
			}
			/* ������ά���õ��ĸ����� */
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			/* �������ñ��뷽ʽΪ��UTF-8 */
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

			Result result = new MultiFormatReader().decode(bitmap, hints);
			String resultStr = result.getText();
			System.out.println("���������ݣ�" + resultStr);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("���ܽ����ö�ά��");
		}
	}

	public static void main(String[] args) throws Exception {
		Logo_Entity_Zxing zxing = new Logo_Entity_Zxing();
		zxing.setContents("�����Ұ��㣡");
		zxing.setCharacterSet("UTF-8");
		zxing.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
		zxing.setFlag(true);
		zxing.setFormat("png");
		zxing.setMargin(0);
		zxing.setWidth(500);
		zxing.setHeight(500);
		zxing.setPath("D:/");
		zxing.setLogoPath("D:\\2.JPG");
		QRLogo_Website code = new QRLogo_Website();
		code.encode(zxing);
		System.out.println("���ɶ�ά��ɹ�");
		String path = url;
		code.decode(path);
	}
}
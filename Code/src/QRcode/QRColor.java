package QRcode;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QRColor {
	public void qrColor() throws IOException {

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			bitMatrix = new QRCodeWriter().encode("生成二维码的内容",
					BarcodeFormat.QR_CODE, 256, 256, hints);

			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(
							x,
							y,
							bitMatrix.get(x, y) == true ? Color.BLACK
									.getRGB() : Color.WHITE.getRGB());
				}
			}
			ImageIO.write(image, "png", new File("D:\\QR_Code_Color.PNG"));
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}

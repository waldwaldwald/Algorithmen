package QRcode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QR {
	public static void main(String[] args) {

		String string = "二二家的小伙伴们，波波爱你们！新的一年希望大家瘦瘦瘦，荷包鼓鼓鼓。摩擦摩擦，在那地板愉快地摩擦。";
		Charset.forName("UTF-8").encode(string);
		ByteArrayOutputStream out = QRCode.from(string).to(ImageType.PNG)
				.withSize(300, 300).stream();

		try {
			FileOutputStream fout = new FileOutputStream(new File(
					"D:\\QR_Cod2e_big.PNG"));

			fout.write(out.toByteArray());

			fout.flush();
			fout.close();

		} catch (FileNotFoundException e) {
			System.out.print("Error:" + e);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			QRColor qrColor = new QRColor();
			qrColor.qrColor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

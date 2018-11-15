package me.next.zxing_android_embedded_ex.decoder;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 从bitmap解码
 * @author hugo
 * 
 */
public class BitmapDecoder {

    static final Collection<BarcodeFormat> PRODUCT_FORMATS;
    static final Collection<BarcodeFormat> ONE_D_FORMATS;
    static final Collection<BarcodeFormat> QR_CODE_FORMATS = EnumSet
            .of(BarcodeFormat.QR_CODE);
    static final Collection<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet
            .of(BarcodeFormat.DATA_MATRIX);
    static {
        PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E,
                BarcodeFormat.EAN_13, BarcodeFormat.EAN_8,
                BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
        ONE_D_FORMATS = EnumSet.of(BarcodeFormat.CODE_39,
                BarcodeFormat.CODE_93, BarcodeFormat.CODE_128,
                BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
    }

	MultiFormatReader multiFormatReader;

	public BitmapDecoder(Context context) {

		multiFormatReader = new MultiFormatReader();

		// 解码的参数
		Hashtable<DecodeHintType, Object> hints = new Hashtable<>(
                2);
		// 可以解析的编码类型
		Vector<BarcodeFormat> decodeFormats = new Vector<>();
		if (decodeFormats.isEmpty()) {
			decodeFormats = new Vector<>();

			// 这里设置可扫描的类型，我这里选择了都支持
			decodeFormats.addAll(ONE_D_FORMATS);
			decodeFormats.addAll(QR_CODE_FORMATS);
			decodeFormats.addAll(DATA_MATRIX_FORMATS);
		}
		hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

		// 设置继续的字符编码格式为UTF8
		hints.put(DecodeHintType.CHARACTER_SET, "UTF8");

		// 设置解析配置参数
		multiFormatReader.setHints(hints);

	}

	/**
	 * 获取解码结果
	 * 
	 * @param bitmap
	 * @return
	 */
	public Result getRawResult(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}

		try {
			return multiFormatReader.decodeWithState(new BinaryBitmap(
					new HybridBinarizer(new BitmapLuminanceSource(bitmap))));
		}
		catch (NotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}

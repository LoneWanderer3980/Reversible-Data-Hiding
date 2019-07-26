public class hiding1 {
	public static void main(String[] args) {
		System.out.println("Start hiding message in 2 pixels");
		
/*�ŧi*/
		imagingRGB test = null;
		int[][] matrix = null;//�x�}
		
/*��J��l�v��*/
		try {
			test = new imagingRGB();
			matrix = test.read("lena_gray.jpg");//�s�J��l�v��
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

/*�񯵱K*/		
		String mm = "https://www.facebook.com/permalink.php?story_fbid=1844961718&id=100049649171009678191844961718&id=100000967819&id=1000009678191844961718&id=1000009678191844961718&id=10000096781961718&id=1000009678191844961718&id=100000967819293k.comk.comk.comkdd/permalink.php?story_fbid=2517471844961718&id=1000009678192931718&id=100000967819293https://www.facebook.com/permalink.php?story_fbid=2517471844961718&id=100000967819293https://www.facebook.com/permalink.php?story_fbid=2517471844961718&id=100000967819293https://www.facebook.com/permalink.php?story_fbid=1844961718&id=100049649171009678191844961718&id=100000967819&id=1000009678191844961718&id=1000009678191844961718&id=10000096781961718&id=1000009678191844961718&id=100000967819293k.comk.comk.comkdd/permalink.php?story_fbid=2517471844961718&id=1000009678192931718&id=100000967819293https://www.facebook.com/permalink.php?story_fbid=2517471844961718&id=100000967819293https://www.facebook.com/permalink.php?story_fbid=2517471844961718&id=100000967819293";
		String message = "";//���s�Ū�
		for (int i = 0; i < 6; i++)
			message += mm;//�s�i���K
		System.out.println("Secret message: " + message);
		int lll = message.length();//���K����
		System.out.println("Secret message length: " + message.length()); 
		
//�ഫ��ƫ��O		
		char[] temp = message.toCharArray();
		String secret = "";
		for (char tmp : temp) {
			secret += String.format("%7s", Integer.toBinaryString((int) tmp)).replace(' ', '0');//��0���N//�o�䤣��ô�
		}
		System.out.println("Secret message: " + secret);//�T�w�S���]����
		System.out.println("Secret message: " + secret.length());//�T�w�S���]����
		System.out.println();
		
/*�쥿��*/       		
		int aaa,bbb;//A'�MB'
        int d,ddd,m;//d�Md'�Mm
		String secret_tmp = secret;
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[y].length; x += 2) {
				if (secret_tmp.length() > 0) {
					int A = matrix[y][x];
					int B = matrix[y][x + 1];
					m = ((A + B) / 2);
					d = A - B;
					ddd = (2 * m) + Integer.parseInt(secret_tmp.substring(0, 1));
					secret_tmp = secret_tmp.substring(1);
					aaa = (int) (m + (Math.floor((double) (ddd + 1) / 2)));
					bbb = (int) (m - (Math.floor((double) ddd / 2)));
					matrix[y][x] = aaa;
					matrix[y][x + 1] = bbb;
				}

			}
		}

		
		
		test.matrix = matrix;
		try {
			test.write("DE-stego2_1.jpg");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		
		
		/*
		System.out.println("Extract data:");
		int[][] matrix2 = null;
		try {
			test = new imagingRGB();
			matrix2 = test.read("DE-stego2_1.jpg");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
*/
		
		String message2 = "";
		String tmp = "";
		String secret2 = "";
		int num = 0;
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[y].length; x += 2) {
				// System.out.println(Math.abs((matrix[y][x]-matrix[y][x+1]) % 2));
				if (num > (lll * 7))
					continue;
				tmp += Math.abs((matrix[y][x] - matrix[y][x + 1]) % 2);
				if (tmp.length() == 7) {
					secret2 += tmp;
					message2 += (char) Integer.parseInt(tmp, 2);
					tmp = "";
				}
				num++;
			}
		}

		System.out.println("Secret message: " + message2);
		System.out.println("Secret message: " + secret2);
		System.out.println("Secret message: " + secret2.length());
		System.out.println();

		int error = 0;
		for (int i = 0; i < message2.length(); i++) {
			if (!message.substring(i, i + 1).equals(message2.substring(i, i + 1)))
				error++;
		}

		System.out.println("Error message: " + error + " " + (float) error / message.length() * 100 + "%");

		error = 0;
		for (int i = 0; i < secret2.length(); i++) {
			if (!secret.substring(i, i + 1).equals(secret2.substring(i, i + 1)))
				error++;
		}

		System.out.println("Error message: " + error + " " + (float) error / secret.length() * 100 + "%");
		System.out.println();

		try {
			new PSNR(test.read("lena_gray.jpg"), matrix);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
        // ����ǷŴ�ͼƬ��filter�����Ƿ�ƽ�����������СͼƬ��filter��Ӱ�죬������������СͼƬ������ֱ������Ϊfalse
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // ���û�����ţ���ô������
            src.recycle(); // �ͷ�Bitmap��native��������
        }
        return dst;
    }

    public static Bitmap decodeSampledBitmapFromFile(String pathName,
                                                     int reqWidth, int reqHeight) {
        // ��һ�ν�����inJustDecodeBounds����Ϊtrue������ȡͼƬ��С
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // �������涨��ķ�������inSampleSizeֵ
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize);
    }
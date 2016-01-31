//把绝对路径转换成content开头的URI
    public Uri geturi(android.content.Intent intent) {

        Uri uri = intent.getData();
        String type = intent.getType();

        if (uri.getScheme().equals("file") && (type.contains("image/"))) {

            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");

                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID}, buff.toString(), null, null);

                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
// set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
//do nothing
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                        Log.i("urishi", uri.toString());
                    }
                }
            }
        }
        return uri;
    }
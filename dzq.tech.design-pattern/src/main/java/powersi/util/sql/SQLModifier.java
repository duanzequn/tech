package powersi.util.sql;

import java.io.*;

public class SQLModifier {
    public static final String javaPath = "dzq.tech.design-pattern/src/main/java/powersi/util/sql/java.txt";
    public static final String sqlPath = "dzq.tech.design-pattern/src/main/java/powersi/util/sql/sql.txt";

    public static void sqlToJava(String bufferName) throws IOException {
        File sqlFile = new File(sqlPath);
        File javaFile = new File(javaPath);
        if (!sqlFile.exists()) {
            sqlFile.createNewFile();
        }
        if (!javaFile.exists()) {
            javaFile.createNewFile();
        }
        FileInputStream sqlFileInputStream = new FileInputStream(sqlFile);
        FileOutputStream javaFileOutputStream = new FileOutputStream(javaFile);
        InputStreamReader sqlInputStreamReader = new InputStreamReader(sqlFileInputStream);
        OutputStreamWriter javaInputStreamWriter = new OutputStreamWriter(javaFileOutputStream);
        BufferedReader sqlBufferedReader = new BufferedReader(sqlInputStreamReader);
        BufferedWriter javaBufferedWriter = new BufferedWriter(javaInputStreamWriter);
        String line;
        while (null != (line = sqlBufferedReader.readLine())) {
            System.out.println(line);
            javaBufferedWriter.write(bufferName + ".append(\" " + line + " \");");
            javaBufferedWriter.newLine();
        }

        sqlBufferedReader.close();
        javaBufferedWriter.close();
    }

    public static void javaToSql(String bufferName) throws IOException {
        File sqlFile = new File(sqlPath);
        File javaFile = new File(javaPath);
        if (!sqlFile.exists()) {
            sqlFile.createNewFile();
        }
        if (!javaFile.exists()) {
            javaFile.createNewFile();
        }
        FileOutputStream sqlFileOutputStream = new FileOutputStream(sqlFile);
        FileInputStream javaFileInputStream = new FileInputStream(javaFile);
        OutputStreamWriter sqlOutputStreamWriter = new OutputStreamWriter(sqlFileOutputStream);
        InputStreamReader javaInputStreamReader = new InputStreamReader(javaFileInputStream);
        BufferedWriter sqlBufferedWriter = new BufferedWriter(sqlOutputStreamWriter);
        BufferedReader javaBufferedReader = new BufferedReader(javaInputStreamReader);

        String line;
        while (null != (line = javaBufferedReader.readLine())) {
            sqlBufferedWriter.write(line.
                    replace(bufferName + ".append(\" ", "")
                    .replace(" \");", ""));
            sqlBufferedWriter.newLine();
        }

        sqlBufferedWriter.close();
        javaBufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        //sqlToJava("sql");
        javaToSql("lSQL");
    }

}

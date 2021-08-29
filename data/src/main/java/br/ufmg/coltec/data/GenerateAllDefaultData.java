package br.ufmg.coltec.data;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.coltec.data.entities.Exercise;

public class GenerateAllDefaultData {

    private static final String TABLE_NAME = "exercise";
    private static final String TABLE_COLUMN_ONE = "name";
    private static final String TABLE_COLUMN_TWO = "description";
    private static final String TABLE_COLUMN_THREE = "type";
    private static final String TABLE_COLUMN_FOUR = "image";
    private List<Exercise> exerciseList;
    private ApplicationDB applicationDB;

    public GenerateAllDefaultData(ApplicationDB applicationDB){
        this.applicationDB = applicationDB;
        exerciseList = new ArrayList<>();
        defineTheme();
        data();
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    private void data(){
        InputStream inputStream;
        Bitmap bitmap;

        if (android.os.Build.VERSION.SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String id = "1cnvGa4FZx2ecmF9mVIEssGp_aGIu4pvu";
        String stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        //BENCH DIPS
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] img = byteArray.toByteArray();
        String name = "Bench Dips";
        String type = "Push";
        String d = "Sente-se em um banco, as mãos próximas às coxas. Ande com os pés para fora e estenda as pernas, levantando o traseiro do banco e segurando-o com os braços estendidos. " +
                "Abaixe o corpo o máximo que puder ou até que os braços formem um ângulo de 90 graus. " +
                "Empurre as palmas das mãos para cima para começar.";
        exerciseList.add(new Exercise(name, d, img, type));

        //BICYCLES
        id = "17QwndQd2bPo07A4zg6K-EtjCx3QWgldW";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Bicicleta";
        type = "Abs";
        d = "Deite-se de costas no chão e coloque as mãos atrás da cabeça. Flexione os joelhos em 90°. Levante as pernas, mas sem as aproximar do peito." +
                "Aproxime o joelho direito do peito e encoste nele com o cotovelo esquerdo. Alterne os membros para fazer a repetição seguinte.";
        exerciseList.add(new Exercise(name, d, img, type));

        //CALF RAISES
        id = "1c9AODxn9gdLI9gNCU-0f6QA3tlIN_KBe";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Calf raises";
        type = "Legs";
        d = "Fique em pé, empurre a planta dos pés e levante o calcanhar até ficar na ponta dos pés. Em seguida, abaixe lentamente de volta ao início.";
        exerciseList.add(new Exercise(name, d, img, type));

        //CHIN UPS
        id = "1BVHhNyD5GkNmxJpLro1C43su3q1MYE6R";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Chin up";
        type = "Pull";
        d = "Com as palmas das mãos viradas para dentro e na largura dos ombros, pendure-se na barra com os cotovelos retos. Puxe-se até que o queixo fique na linha da barra e lentamente volte à posição inicial.";
        exerciseList.add(new Exercise(name, d, img, type));

        //CRUNCH
        id = "1CyVmpouP8QaFAcAvoVbRjcyA1AWU-OyZ";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Crunch";
        type = "Abs";
        d = "Deite-se de costas no chão. Flexione os joelhos e mantenha os pés separados na largura dos ombros e apoiados no chão. Posicione as mãos atrás da cabeça ou mantenha-as cruzadas em frente do corpo. Contraia os músculo dos abdômen e erga o tronco (até aproximá-lo dos joelhos ou somente até tirar a parte de cima das costas do chão). Retorne à posição inicial.";
        exerciseList.add(new Exercise(name, d, img, type));

        //DIAMOND
        id = "1msOmwaawVKKDzhKMp9An39GUIvGMcuHz";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Flexão diamante";
        type = "Push";
        d = "Mova-se para a posição da flexão tradicional. Una os dedos indicadores e os polegares. Essa posição dá forma ao diamante, também conhecido como uma pirâmide. " +
                "Abaixe-se até o chão e, em seguida, comece a flexão.";
        exerciseList.add(new Exercise(name, d, img, type));

        //LUNGE
        id = "166TZ9NF4eVhAA-zmqnhoxTQCXIp1uLEv";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Lunge";
        type = "Legs";
        d = "Em pé, dê um passo à frente com uma das pernas (de forma que, quando você flexionar os joelhos, a perna de trás fique abaixo do tronco e forme um ângulo de 90 graus). Flexione o joelho e desça lentamente até que o joelho da perna de trás quase toque o chão. Estenda o joelho até perto da posição inicial.";
        exerciseList.add(new Exercise(name, d, img, type));

        //PLANK
        id = "1Eb8E88BwD61U-MjQQLXNrGbBKtQAqbfR";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Plank";
        type = "Abs";
        d = "Deite de barriga para baixo. Apoie os antebraços no chão. Os ombros devem estar alinhados na linha do cotovelo, e as mãos devem estar voltadas para cima. Com o corpo alinhado, levante o quadril.";
        exerciseList.add(new Exercise(name, d, img, type));

        //PULL UP
        id = "1Hdh-2TPXmoxS4zm5SwAT4kMOgnfZV4Ih";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Pull up";
        type = "Pull";
        d = "Com as palmas das mãos viradas para fora e na largura dos ombros, pendure-se na barra com os cotovelos retos. Puxe-se até que o queixo fique na linha da barra e lentamente volte à posição inicial.";
        exerciseList.add(new Exercise(name, d, img, type));

        //PUSH UP
        id = "16BwK1Qo2W0rtU2RemZRa7c1qZ-5ca6jU";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Flexão";
        type = "Push";
        d = "Apoie as mãos no solo na mesma linha do peitoral, afastadas em uma largura um pouco maior do que a dos ombros. Flexione os cotovelos, sem encostar o corpo no solo.";
        exerciseList.add(new Exercise(name, d, img, type));

        //SQUAT
        id = "1ioAPRB-C6tKgWTtoilZeOhe1I94Vgwdn";
        stringUrl = "https://drive.google.com/uc?export=view&id=" + id;

        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            bitmap = null;
        }

        byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        img = byteArray.toByteArray();
        name = "Agachamento";
        type = "Legs";
        d = "Agache na amplitude máxima do movimento, como se você fosse sentar em uma cadeira, mas não tire os calcanhares do chão ou curve as costas.";
        exerciseList.add(new Exercise(name, d, img, type));

        for(Exercise e : exerciseList){
           insert(e);
        }
    }

    private void insert(Exercise e){
        try{
            ContentValues params = new ContentValues();
            params.put(TABLE_COLUMN_ONE, e.getName());
            params.put(TABLE_COLUMN_TWO, e.getDescription());
            params.put(TABLE_COLUMN_THREE, e.getType());
            params.put(TABLE_COLUMN_FOUR, e.getImage());

            applicationDB.getWritableDatabase().insert(TABLE_NAME, null, params);
            Log.d("INSERT_DEFAULT_EXERCISES", "SUCESS");
        }catch (Exception error){
            error.printStackTrace();
            Log.d("INSERT_DEFAULT_EXERCISES", "FAILED");
        }finally {
            //this.db.close();
        }
    }

    private void defineTheme(){
        Log.d("DEFINE_THEME", "WHITE THEME");
        SharedPreferences pref = ApplicationDB.getContext().getSharedPreferences("Theme", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("themeKey", true); //white theme
        editor.commit();
    }
}

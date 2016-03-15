package tasks.pictureparser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Allexb.
 */
public class FileNameGenerator {
    private final String originalName;
    private String imageType;
    private int nameCount = 0;

    public FileNameGenerator(String originalName) {
        Date date = new Date();
        imageType = originalName.substring(originalName.lastIndexOf('.') + 1);
        this.originalName = originalName.substring(0, originalName.lastIndexOf('.')) + new SimpleDateFormat("yyMMddHHmmss").format(date);
    }

    public String nextName() {
        nameCount++;
        String newName = originalName;
        if (nameCount < 10 && nameCount < 100) {
            newName = newName + "_00" + nameCount;
        } else if (nameCount < 100){
            newName = newName + "_0" + nameCount;
        } else {
            newName = newName + "_" + nameCount;
        }
        newName = newName + "." + imageType;
        return newName;
    }
}

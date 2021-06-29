package webParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.opencsv.CSVWriter;

public class WriteOnCSV {
    public void writer(List<RawData> rawDatas) {
        try {
            File file = new File("./roadplus.csv");
            if (!file.exists()) {
                CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file, true),"MS949"));
                String[] name = {"일시", "노선번호", "노선명", "방향", "IC/JC명", "IC/JC간거리(km)", "이동속도(km/h)", "상태(색)", "상태"};
                cw.writeNext(null);
                cw.close();
            }
            CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file, true),"MS949"));
            
            for (int i = 0; i < rawDatas.size(); i++) {
                RawData raw = rawDatas.get(i);
                cw.writeNext(raw.getWriteOnCSV());
            }
            cw.close();
        } catch (Exception e) {
            
        }
    }
}

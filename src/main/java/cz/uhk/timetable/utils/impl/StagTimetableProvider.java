package cz.uhk.timetable.utils.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import cz.uhk.timetable.model.LocationTimetable;
import cz.uhk.timetable.utils.StagTimeAdapter;
import cz.uhk.timetable.utils.TimetableProvider;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

public class StagTimetableProvider implements TimetableProvider {


    @Override
    public LocationTimetable read(String building, String room) {

        //1. Připravit Url pro načtení ze stagu https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=%25&budova=J&mistnost=J1&outputFormat=JSON

        var url = "https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=LS&budova=%s&mistnost=%s&outputFormat=JSON"
                        .formatted(building, room);

        //2. Pripojit se k serveru
        //3. Vyrobit Parser
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, new StagTimeAdapter()).create();

        try {
            URL server = new URL(url);

            return gson.fromJson(new InputStreamReader(server.openStream()), LocationTimetable.class);
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //4. Načíst data rozvrhu pomoci parseru

    }
}

package com.lollipop.spider.pokedex.pokemon;

import com.lollipop.spider.net.BaseSpiderHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
public class EncounterResponse {
    private List<Encounter> data;
    private Integer code;

    public static class EncounterHandler implements BaseSpiderHandler<EncounterResponse> {

        @Override
        public EncounterResponse parseData(Document document) {
            try {
                Elements firstHead = document.select("h1#firstHeading");
                List<Encounter> encounters = new ArrayList<>();
                Element e1 = document.getElementById("获得方式").parent().nextElementSibling();
                Elements e2 = e1.select(".bgwhite");
                for (Element e3 : e2) {
                    Encounter encounter = new Encounter();
                    Elements e4 = e3.children();
                    encounter.setPokeName(firstHead.text());
                    encounter.setGameVersion(e4.get(0).text().replace("  ", "|").replace(" ", ""));
                    encounter.setArea(e4.get(1).text());
                    encounter.setMethod(e4.get(2).text());
                    encounter.setRemark(e4.get(3).text());
                    encounters.add(encounter);
                }
                return new EncounterResponse(encounters, 0);
            } catch (Exception e) {
                return new EncounterResponse(null, 500);
            }
        }
    }

    @Data
    public static class Encounter {
        private Long id;
        private Integer pokeId;
        private String pokeName;
        private String gameVersion;
        private String gameGeneration;
        private String area;
        private String method;
        private String remark;
    }
}

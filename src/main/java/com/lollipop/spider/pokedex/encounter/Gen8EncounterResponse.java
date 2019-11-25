package com.lollipop.spider.pokedex.encounter;

import com.lollipop.spider.net.BaseSpiderHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzh
 * @date 2019/11/25 17:39
 **/
@Data
@AllArgsConstructor
public class Gen8EncounterResponse {

    private List<List<String>> data;
    private String area;

    public static class EncounterHandler implements BaseSpiderHandler<Gen8EncounterResponse> {

        @Override
        public Gen8EncounterResponse parseData(Document document) {
            Element e2 = document.select(".GSTableBox").first();
            Element title = e2.previousElementSibling().previousElementSibling();

            Elements trs = e2.select("tr");
            List<List<String>> data = new ArrayList<>();
            String way = "";
            for (Element tr : trs) {
                List<String> row = new ArrayList<>();
                String[] trString = tr.text().split(" ");
                if (tr.html().contains("rowspan")) {
                    way = trString[0];
                    row = Arrays.asList(trString);
                } else {
                    if (StringUtils.isNotEmpty(way)) {
                        row.add(way);
                    }
                    row.addAll(Arrays.asList(trString));
                }
                data.add(row);
            }
            return new Gen8EncounterResponse(data, title.text());
        }
    }

}

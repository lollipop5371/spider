package com.lollipop.spider.pokedex.encounter;

import com.lollipop.spider.net.BaseSpiderHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2019/11/25 17:39
 **/
@Data
@AllArgsConstructor
@Slf4j
public class Gen8EncounterResponse {

    private List<List<String>> data;
    private String area;

    public static class EncounterHandler implements BaseSpiderHandler<Gen8EncounterResponse> {

        @Override
        public Gen8EncounterResponse parseData(Document document) {
            Element e2 = document.select(".GSTableBox").first();
            Element title = e2.previousElementSibling().previousElementSibling();

            Elements trs = e2.select("tr");
            int maxWidth = 0;
            int height = trs.size();
            List<List<String>> data = new ArrayList<>(height);
            for (Element tr : trs) {
                maxWidth = Math.max(maxWidth, tr.children().size());
                data.add(new ArrayList<>());
            }
            log.info("maxWidth:{},height:{}", maxWidth, height);

            for (int row = 0; row < height; ++row) {
                int hang = 0;
                List<String> rowData = data.get(row);
                for (Element td : trs.get(row).children()) {
                    String text = td.text();
                    String rowSpan = td.attr("rowspan");
                    if (StringUtils.isNotEmpty(rowSpan)) {
                        //向下n行写入数据
                        int rspan = Integer.parseInt(rowSpan);
                        while (--rspan > 0) {
                            List<String> nextRowData = data.get(row + rspan);
                            nextRowData.add(hang, text);
                        }
                    }

                    rowData.add(text);
                    hang++;
                }
            }

            return new Gen8EncounterResponse(data, title.text());
        }
    }

}

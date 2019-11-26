package com.lollipop.spider;

import com.alibaba.fastjson.JSON;
import com.lollipop.spider.api.HandBookApi;
import com.lollipop.spider.pokedex.encounter.Gen8EncounterResponse;
import com.lollipop.spider.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpiderApplicationTests {
//    @Autowired
//    PokemonService pokemonService;


    /**
     * 爬取精灵出处
     *
     * @throws Exception
     */
//    @Test
//    public void spiderEncounter() throws Exception {
//        int startIndex = 801;
//        int maxIndex = 808;
//        while (startIndex < maxIndex) {
//            List<EncounterResponse.Encounter> list = new ArrayList<>();
//            PokeApi pokeApi = new PokeApiClient();
//            for (int i = 0; i < 10; ++i) {
//                startIndex++;
//                PokemonSpecies pokemonSpecies;
//                try {
//                    pokemonSpecies = pokeApi.getPokemonSpecies(startIndex);
//                    String name = CharacterUtil.captureName(pokemonSpecies.getName());
//                    EncounterResponse response = pokemonService.getPokemonEncounterList(name);
//                    if (response != null && response.getCode() == 0) {
//                        System.out.println(startIndex + "   " + name);
//                        List<EncounterResponse.Encounter> resList = response.getData();
//                        int finalI = startIndex;
//                        resList.forEach(encounter -> {
//                            encounter.setPokeId(finalI);
//                        });
//                        list.addAll(resList);
//                    }
//                } catch (Exception e) {
//                    break;
//                }
//            }
//
//            String filePath = "G:\\spider\\pokemon\\encounter.xlsx";
//            File file = new File(filePath);
//            InputStream in = new FileInputStream(file);
//            Workbook excel = new XSSFWorkbook(in);
//            Sheet sheet = excel.getSheetAt(0);
//            int i = sheet.getLastRowNum();
//            for (EncounterResponse.Encounter encounter : list) {
//                Row row = sheet.createRow(++i);
//                row.createCell(0).setCellValue(encounter.getPokeId());
//                row.createCell(1).setCellValue(encounter.getPokeName());
//                row.createCell(2).setCellValue(encounter.getGameVersion());
//                row.createCell(3).setCellValue(encounter.getArea());
//                row.createCell(4).setCellValue(encounter.getMethod());
//                row.createCell(5).setCellValue(encounter.getRemark());
//            }
//
//            FileOutputStream fos = new FileOutputStream(filePath);
//            excel.write(fos);
//            fos.close();
//
//            Thread.sleep(40);
//        }
//
//    }
    @Test
    public void test2() throws Exception {
        String filePath = "C:\\Users\\bleege\\Desktop\\gen8.xlsx";
        Workbook excel = new XSSFWorkbook();
        for (int i = 1; i <= 40; ++i) {
            Gen8EncounterResponse response = HandBookApi.gen8Encounter(i, new Gen8EncounterResponse.EncounterHandler());
            ExcelUtils.buildSheet(excel, response.getArea(), response.getData());
        }

        FileOutputStream fos = new FileOutputStream(filePath);
        excel.write(fos);
        fos.close();
    }

    @Test
    public void test3() throws Exception {
        Gen8EncounterResponse response = HandBookApi.gen8Encounter(3, new Gen8EncounterResponse.EncounterHandler());
        System.out.println(JSON.toJSONString(response));
    }
}

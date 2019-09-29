package com.lollipop.spider;

import com.lollipop.spider.pokedex.pokemon.EncounterResponse;
import com.lollipop.spider.service.PokemonService;
import com.lollipop.spider.util.CharacterUtil;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {
    @Autowired
    PokemonService pokemonService;


    /**
     * 爬取精灵出处
     *
     * @throws Exception
     */
    @Test
    public void spiderEncounter() throws Exception {
        int startIndex = 801;
        int maxIndex = 808;
        while (startIndex < maxIndex) {
            List<EncounterResponse.Encounter> list = new ArrayList<>();
            PokeApi pokeApi = new PokeApiClient();
            for (int i = 0; i < 10; ++i) {
                startIndex++;
                PokemonSpecies pokemonSpecies;
                try {
                    pokemonSpecies = pokeApi.getPokemonSpecies(startIndex);
                    String name = CharacterUtil.captureName(pokemonSpecies.getName());
                    EncounterResponse response = pokemonService.getPokemonEncounterList(name);
                    if (response != null && response.getCode() == 0) {
                        System.out.println(startIndex + "   " + name);
                        List<EncounterResponse.Encounter> resList = response.getData();
                        int finalI = startIndex;
                        resList.forEach(encounter -> {
                            encounter.setPokeId(finalI);
                        });
                        list.addAll(resList);
                    }
                } catch (Exception e) {
                    break;
                }
            }

            String filePath = "G:\\spider\\pokemon\\encounter.xlsx";
            File file = new File(filePath);
            InputStream in = new FileInputStream(file);
            Workbook excel = new XSSFWorkbook(in);
            Sheet sheet = excel.getSheetAt(0);
            int i = sheet.getLastRowNum();
            for (EncounterResponse.Encounter encounter : list) {
                Row row = sheet.createRow(++i);
                row.createCell(0).setCellValue(encounter.getPokeId());
                row.createCell(1).setCellValue(encounter.getPokeName());
                row.createCell(2).setCellValue(encounter.getGameVersion());
                row.createCell(3).setCellValue(encounter.getArea());
                row.createCell(4).setCellValue(encounter.getMethod());
                row.createCell(5).setCellValue(encounter.getRemark());
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            excel.write(fos);
            fos.close();

            Thread.sleep(40);
        }

    }

}

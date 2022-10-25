package com.example.estacaometeorologica.mapper;

import com.example.estacaometeorologica.controller.dto.TTNUplinkDto;
import com.example.estacaometeorologica.mapper.dto.DadosColetadosCSV;
import com.example.estacaometeorologica.model.DadosColetados;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DadosColetadosMapper {
    private DadosColetadosMapper() {
        //
    }

    public static DadosColetados mapToEntity(TTNUplinkDto dto) throws IOException {
        String webPage = "https://pt.allmetsat.com/metar-taf/brasil-sao-paulo-rio.php?icao=SBST";
        String html = Jsoup.connect(webPage).get().html();
        //System.out.println(html);
        //System.out.println("////////////////////////////////////");
        String pressao = html.substring(html.indexOf("<div class=\"mt\">\n" +
                "     Pressão")+10,html.indexOf("hPa\n" +
                "    </div>\n" +
                "    <div class=\"mt\">"));
        pressao = pressao.replaceAll("\\D", "");
        //System.out.println(pressao);

        Random rand = new Random();
        if (dto == null || dto.getMessage() == null || dto.getMessage().getDadosColetados() == null
                || dto.getMessage().getDadosColetados().getDirecaoVento() == null
                || dto.getMessage().getDadosColetados().getPrecipitacao() == null
                || dto.getMessage().getDadosColetados().getPressaoAtmosferica() == null
                || dto.getMessage().getDadosColetados().getTemperatura() == null
                || dto.getMessage().getDadosColetados().getUmidadeAr() == null
                || dto.getMessage().getDadosColetados().getVelocidadeVento() == null
                || dto.getMessage().getDadosColetados().getCartaoSD() == null) {
            throw new IllegalArgumentException("Formato de dados coletados inválido.");
        }

        final TTNUplinkDto.InternalDadosColetadosDto dadosDoTTN = dto.getMessage().getDadosColetados();

        double vento = dadosDoTTN.getVelocidadeVento() * (20 + rand.nextInt(2) - 1 + rand.nextInt(3) - 1);

        if(vento > 20){
            vento = vento - 7;
        }

        return new DadosColetados(
                Instant.now().minusSeconds(10800),
                dadosDoTTN.getPrecipitacao(),
                vento,
                dadosDoTTN.getDirecaoVento(),
                dadosDoTTN.getTemperatura(),
                dadosDoTTN.getUmidadeAr(),
                Double.parseDouble(pressao) - 9 + rand.nextInt(4) - 1 + rand.nextInt(3) - 1,
                dadosDoTTN.getNivelBateria(),
                dadosDoTTN.getCartaoSD()
        );
    }

    public static List<DadosColetadosCSV> mapToCSVList(List<DadosColetados> dadosColetados) {
        return dadosColetados.stream().map(DadosColetadosCSV::new).collect(Collectors.toList());
    }
}

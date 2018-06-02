package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.parser.entity.Vacancy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class ParserJsop.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 08.05.2018.
 */
public class ParserJsop {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(ParserVacancies.class);

    /**
     * Start parsing site sql.ru
     * @param url url for parsing.
     * @param date date start parsing.
     * @return list found vacancy.
     */
    public List<Vacancy> start(String url, LocalDateTime date) {
        List<Vacancy> listVacancy = new ArrayList<>();
        boolean work = true;
        int i = 1;
        try {
            while (work) {
                logger.debug("Parsing page №{} of {}", i, url);
                Document document = Jsoup.connect(String.format("%s/%d", url, i)).get();
                Elements elements = document.getElementsByAttributeValue("class", "forumTable").select("tr");
                for (Element element : elements) {
                    if (verifyVacancy(element.getElementsByAttributeValue("class", "postslisttopic").text())) {
                        LocalDateTime dateVacancy = parseDate(element.child(5).text());
                        if (dateVacancy == null || date.isAfter(dateVacancy)) {
                            work = false;
                            break;
                        }
                        String nameVacancy = element.getElementsByAttributeValue("class", "postslisttopic").first().child(0).text();
                        String authorVacancy = element.child(2).text();
                        String urlVacancy = element.getElementsByAttributeValue("class", "postslisttopic").first().child(0).attr("href");
                        Document documentVacancy = Jsoup.connect(urlVacancy).get();
                        String textVacancy = documentVacancy.getElementsByAttributeValue("class", "msgBody").get(1).text();

                        listVacancy.add(new Vacancy(nameVacancy, textVacancy, authorVacancy, urlVacancy, dateVacancy));
                    }
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listVacancy;
    }

    /**
     * Verify vacancy.
     * @param text text for verifying.
     * @return tru if successful.
     */
    private boolean verifyVacancy(String text) {
        Pattern pattern = Pattern.compile("java[^script]");
        return pattern.matcher(text.toLowerCase()).find();
    }

    /**
     * Parse date from vacancy.
     * @param date string representation date.
     * @return instance LocalDateTime.
     */
    private LocalDateTime parseDate(String date) {
        LocalDateTime result = null;
        LocalTime localTime;
        if (date.contains("сегодня")) {
            date = date.replace("сегодня, ", "");
            localTime = LocalTime.parse(date, DateTimeFormatter.ofPattern("HH:mm"));
            result = LocalDateTime.of(LocalDate.now(), localTime);
        } else if (date.contains("вчера")) {
            date = date.replace("вчера, ", "");
            localTime = LocalTime.parse(date, DateTimeFormatter.ofPattern("HH:mm"));
            result = LocalDateTime.of(LocalDate.now(), localTime).minusDays(1);
        } else {
            if (date.contains("май")) {
                date = date.replace("май", "мая");
            }
            result = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d MMM yy, HH:mm"));
        }
        return result;
    }
}
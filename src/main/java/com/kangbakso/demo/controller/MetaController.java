package com.kangbakso.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaController {
    @GetMapping("/metatag")
    public String metatag() {
        String html = "<html>\n";
        html += "<head>\n";
        html += "<title>Test Application</title>\n";
        html += "<meta name=\"title\" content=\"Kangbakso — Testing Meta Tag\">\n";
        html += "<meta name=\"description\" content=\"Testing meta meta meta meta meta meta\">\n";

        // Twitter
        html += "<meta name=\"twitter:card\" content=\"summary\">\n";
        html += "<meta name=\"twitter:domain\" content=\"kangbakso.com\">\n";
        html += "<meta name=\"twitter:url\" content=\"http://kangbakso.com/\">\n";
        html += "<meta name=\"twitter:title\" content=\"Testing Application Meta Tag Twitter\">\n";
        html += "<meta name=\"twitter:description\" content=\"Testing meta meta meta meta meta meta Twitter\">\n";
        html += "<meta name=\"twitter:image\" content=\"https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png\">\n";

        // Facebook
        html += "<meta property=\"og:type\" content=\"website\">\n";
        html += "<meta property=\"og:url\" content=\"http://kangbakso.com/\">\n";
        html += "<meta property=\"og:title\" content=\"Testing Application OG Meta Tag Facebook\">\n";
        html += "<meta property=\"og:description\" content=\"Testing meta meta meta meta meta meta Facebook\">\n";
        html += "<meta property=\"og:image\" content=\"https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png\">\n";
        html += "<meta property=\"og:image:type\" content=\"image/png\">\n";
        html += "<meta property=\"og:image:width\" content=\"300\">\n";
        html += "<meta property=\"og:image:height\" content=\"300\">\n";

        html += "<meta property=\"description\" content=\"Testing meta meta meta meta meta meta\">\n";
        html += "<meta property=\"image\" content=\"https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png\">\n";

        html += "</head>\n";
        html += "<body>\n";
        html += "<h1>Test title for this application</h1>\n";
        html += "</body>\n";
        html += "</html>\n";
        return html;
    }
}

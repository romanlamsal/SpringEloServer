package de.lamsal.elo.smash.smashelo.apis

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class StaticController {

    @RequestMapping(value = ["/", "/admin"])
    fun gui() : String {
        return "index.html"
    }
}
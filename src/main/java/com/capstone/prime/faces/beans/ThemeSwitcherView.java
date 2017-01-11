package com.capstone.prime.faces.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.capstone.prime.faces.beans.Theme;
import com.capstone.prime.faces.beans.ThemeService;
/**
 * Created by  ZhaoC on 10/17/16.
 */

@ManagedBean
public class ThemeSwitcherView {

    private List<Theme> themes;

    @ManagedProperty("#{themeService}")
    private ThemeService service;

    @PostConstruct
    public void init() {
        themes = service.getThemes();
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }
}

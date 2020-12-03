package com.rentalfilm.msaclientui.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.rentalfilm.msaclientui.bean.FilmBean;

@FeignClient(
		name = "edge-zuul",
		contextId = "filmContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-film")
public interface FilmProxy {
	
	@GetMapping("/msa-film/film/public/get-all")
	public List<FilmBean> getAllFilm();
	
	//...
}

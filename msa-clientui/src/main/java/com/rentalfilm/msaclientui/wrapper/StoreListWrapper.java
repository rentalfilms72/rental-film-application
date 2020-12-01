package com.rentalfilm.msaclientui.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.rentalfilm.msaclientui.bean.StoreBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class StoreListWrapper {
	
	List<StoreBean> storeList = new ArrayList<>();
	
}

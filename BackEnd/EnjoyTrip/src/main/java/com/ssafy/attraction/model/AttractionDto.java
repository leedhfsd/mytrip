package com.ssafy.attraction.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AttractionDto {
	private int contentId;
    private String title;
    private String addr1;
    private String firstImage;
    private String firstImage2;
    private int contentTypeId;
    private String contentName;
    private double rating;
    private int ratingCount;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String homepage;
    private String overview;
    private String tel;
    private int favorite;
    private int sidoCode;
    private int gugunCode;
    private int isLike;
    
}

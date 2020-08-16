package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:复合排行榜(包括好评榜、热播榜、北美榜、大陆榜)中的所有记录实例
 * @Author: ggh
 * @Date: 2020/8/13 10:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MultipleRankings {

    private List<MultipleRanking> multipleRankings;

}

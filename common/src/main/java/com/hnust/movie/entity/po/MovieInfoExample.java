package com.hnust.movie.entity.po;

import java.util.ArrayList;
import java.util.List;

public class MovieInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Long value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Long value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Long value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Long value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Long value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Long value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Long> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Long> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Long value1, Long value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Long value1, Long value2) {
            addCriterion("mid not between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMovieNameIsNull() {
            addCriterion("movie_name is null");
            return (Criteria) this;
        }

        public Criteria andMovieNameIsNotNull() {
            addCriterion("movie_name is not null");
            return (Criteria) this;
        }

        public Criteria andMovieNameEqualTo(String value) {
            addCriterion("movie_name =", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameNotEqualTo(String value) {
            addCriterion("movie_name <>", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameGreaterThan(String value) {
            addCriterion("movie_name >", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameGreaterThanOrEqualTo(String value) {
            addCriterion("movie_name >=", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameLessThan(String value) {
            addCriterion("movie_name <", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameLessThanOrEqualTo(String value) {
            addCriterion("movie_name <=", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameLike(String value) {
            addCriterion("movie_name like", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameNotLike(String value) {
            addCriterion("movie_name not like", value, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameIn(List<String> values) {
            addCriterion("movie_name in", values, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameNotIn(List<String> values) {
            addCriterion("movie_name not in", values, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameBetween(String value1, String value2) {
            addCriterion("movie_name between", value1, value2, "movieName");
            return (Criteria) this;
        }

        public Criteria andMovieNameNotBetween(String value1, String value2) {
            addCriterion("movie_name not between", value1, value2, "movieName");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIsNull() {
            addCriterion("img_urls is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIsNotNull() {
            addCriterion("img_urls is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlsEqualTo(String value) {
            addCriterion("img_urls =", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotEqualTo(String value) {
            addCriterion("img_urls <>", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsGreaterThan(String value) {
            addCriterion("img_urls >", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsGreaterThanOrEqualTo(String value) {
            addCriterion("img_urls >=", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLessThan(String value) {
            addCriterion("img_urls <", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLessThanOrEqualTo(String value) {
            addCriterion("img_urls <=", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLike(String value) {
            addCriterion("img_urls like", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotLike(String value) {
            addCriterion("img_urls not like", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIn(List<String> values) {
            addCriterion("img_urls in", values, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotIn(List<String> values) {
            addCriterion("img_urls not in", values, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsBetween(String value1, String value2) {
            addCriterion("img_urls between", value1, value2, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotBetween(String value1, String value2) {
            addCriterion("img_urls not between", value1, value2, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andDirectorsIsNull() {
            addCriterion("directors is null");
            return (Criteria) this;
        }

        public Criteria andDirectorsIsNotNull() {
            addCriterion("directors is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorsEqualTo(String value) {
            addCriterion("directors =", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsNotEqualTo(String value) {
            addCriterion("directors <>", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsGreaterThan(String value) {
            addCriterion("directors >", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsGreaterThanOrEqualTo(String value) {
            addCriterion("directors >=", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsLessThan(String value) {
            addCriterion("directors <", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsLessThanOrEqualTo(String value) {
            addCriterion("directors <=", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsLike(String value) {
            addCriterion("directors like", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsNotLike(String value) {
            addCriterion("directors not like", value, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsIn(List<String> values) {
            addCriterion("directors in", values, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsNotIn(List<String> values) {
            addCriterion("directors not in", values, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsBetween(String value1, String value2) {
            addCriterion("directors between", value1, value2, "directors");
            return (Criteria) this;
        }

        public Criteria andDirectorsNotBetween(String value1, String value2) {
            addCriterion("directors not between", value1, value2, "directors");
            return (Criteria) this;
        }

        public Criteria andScreenWriterIsNull() {
            addCriterion("screen_writer is null");
            return (Criteria) this;
        }

        public Criteria andScreenWriterIsNotNull() {
            addCriterion("screen_writer is not null");
            return (Criteria) this;
        }

        public Criteria andScreenWriterEqualTo(String value) {
            addCriterion("screen_writer =", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterNotEqualTo(String value) {
            addCriterion("screen_writer <>", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterGreaterThan(String value) {
            addCriterion("screen_writer >", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterGreaterThanOrEqualTo(String value) {
            addCriterion("screen_writer >=", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterLessThan(String value) {
            addCriterion("screen_writer <", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterLessThanOrEqualTo(String value) {
            addCriterion("screen_writer <=", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterLike(String value) {
            addCriterion("screen_writer like", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterNotLike(String value) {
            addCriterion("screen_writer not like", value, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterIn(List<String> values) {
            addCriterion("screen_writer in", values, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterNotIn(List<String> values) {
            addCriterion("screen_writer not in", values, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterBetween(String value1, String value2) {
            addCriterion("screen_writer between", value1, value2, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andScreenWriterNotBetween(String value1, String value2) {
            addCriterion("screen_writer not between", value1, value2, "screenWriter");
            return (Criteria) this;
        }

        public Criteria andMainActorsIsNull() {
            addCriterion("main_actors is null");
            return (Criteria) this;
        }

        public Criteria andMainActorsIsNotNull() {
            addCriterion("main_actors is not null");
            return (Criteria) this;
        }

        public Criteria andMainActorsEqualTo(String value) {
            addCriterion("main_actors =", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsNotEqualTo(String value) {
            addCriterion("main_actors <>", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsGreaterThan(String value) {
            addCriterion("main_actors >", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsGreaterThanOrEqualTo(String value) {
            addCriterion("main_actors >=", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsLessThan(String value) {
            addCriterion("main_actors <", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsLessThanOrEqualTo(String value) {
            addCriterion("main_actors <=", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsLike(String value) {
            addCriterion("main_actors like", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsNotLike(String value) {
            addCriterion("main_actors not like", value, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsIn(List<String> values) {
            addCriterion("main_actors in", values, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsNotIn(List<String> values) {
            addCriterion("main_actors not in", values, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsBetween(String value1, String value2) {
            addCriterion("main_actors between", value1, value2, "mainActors");
            return (Criteria) this;
        }

        public Criteria andMainActorsNotBetween(String value1, String value2) {
            addCriterion("main_actors not between", value1, value2, "mainActors");
            return (Criteria) this;
        }

        public Criteria andCategoriesIsNull() {
            addCriterion("categories is null");
            return (Criteria) this;
        }

        public Criteria andCategoriesIsNotNull() {
            addCriterion("categories is not null");
            return (Criteria) this;
        }

        public Criteria andCategoriesEqualTo(String value) {
            addCriterion("categories =", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotEqualTo(String value) {
            addCriterion("categories <>", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThan(String value) {
            addCriterion("categories >", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThanOrEqualTo(String value) {
            addCriterion("categories >=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThan(String value) {
            addCriterion("categories <", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThanOrEqualTo(String value) {
            addCriterion("categories <=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLike(String value) {
            addCriterion("categories like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotLike(String value) {
            addCriterion("categories not like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesIn(List<String> values) {
            addCriterion("categories in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotIn(List<String> values) {
            addCriterion("categories not in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesBetween(String value1, String value2) {
            addCriterion("categories between", value1, value2, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotBetween(String value1, String value2) {
            addCriterion("categories not between", value1, value2, "categories");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(String value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(String value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(String value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(String value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(String value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLike(String value) {
            addCriterion("release_date like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotLike(String value) {
            addCriterion("release_date not like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<String> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<String> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(String value1, String value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(String value1, String value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNull() {
            addCriterion("run_time is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNotNull() {
            addCriterion("run_time is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeEqualTo(String value) {
            addCriterion("run_time =", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotEqualTo(String value) {
            addCriterion("run_time <>", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThan(String value) {
            addCriterion("run_time >", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThanOrEqualTo(String value) {
            addCriterion("run_time >=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThan(String value) {
            addCriterion("run_time <", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThanOrEqualTo(String value) {
            addCriterion("run_time <=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLike(String value) {
            addCriterion("run_time like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotLike(String value) {
            addCriterion("run_time not like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIn(List<String> values) {
            addCriterion("run_time in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotIn(List<String> values) {
            addCriterion("run_time not in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeBetween(String value1, String value2) {
            addCriterion("run_time between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotBetween(String value1, String value2) {
            addCriterion("run_time not between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andSummeryIsNull() {
            addCriterion("summery is null");
            return (Criteria) this;
        }

        public Criteria andSummeryIsNotNull() {
            addCriterion("summery is not null");
            return (Criteria) this;
        }

        public Criteria andSummeryEqualTo(String value) {
            addCriterion("summery =", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryNotEqualTo(String value) {
            addCriterion("summery <>", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryGreaterThan(String value) {
            addCriterion("summery >", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryGreaterThanOrEqualTo(String value) {
            addCriterion("summery >=", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryLessThan(String value) {
            addCriterion("summery <", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryLessThanOrEqualTo(String value) {
            addCriterion("summery <=", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryLike(String value) {
            addCriterion("summery like", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryNotLike(String value) {
            addCriterion("summery not like", value, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryIn(List<String> values) {
            addCriterion("summery in", values, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryNotIn(List<String> values) {
            addCriterion("summery not in", values, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryBetween(String value1, String value2) {
            addCriterion("summery between", value1, value2, "summery");
            return (Criteria) this;
        }

        public Criteria andSummeryNotBetween(String value1, String value2) {
            addCriterion("summery not between", value1, value2, "summery");
            return (Criteria) this;
        }

        public Criteria andRatingNumIsNull() {
            addCriterion("rating_num is null");
            return (Criteria) this;
        }

        public Criteria andRatingNumIsNotNull() {
            addCriterion("rating_num is not null");
            return (Criteria) this;
        }

        public Criteria andRatingNumEqualTo(Float value) {
            addCriterion("rating_num =", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotEqualTo(Float value) {
            addCriterion("rating_num <>", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumGreaterThan(Float value) {
            addCriterion("rating_num >", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumGreaterThanOrEqualTo(Float value) {
            addCriterion("rating_num >=", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumLessThan(Float value) {
            addCriterion("rating_num <", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumLessThanOrEqualTo(Float value) {
            addCriterion("rating_num <=", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumIn(List<Float> values) {
            addCriterion("rating_num in", values, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotIn(List<Float> values) {
            addCriterion("rating_num not in", values, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumBetween(Float value1, Float value2) {
            addCriterion("rating_num between", value1, value2, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotBetween(Float value1, Float value2) {
            addCriterion("rating_num not between", value1, value2, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNull() {
            addCriterion("off_shelf is null");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNotNull() {
            addCriterion("off_shelf is not null");
            return (Criteria) this;
        }

        public Criteria andOffShelfEqualTo(Integer value) {
            addCriterion("off_shelf =", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotEqualTo(Integer value) {
            addCriterion("off_shelf <>", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThan(Integer value) {
            addCriterion("off_shelf >", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThanOrEqualTo(Integer value) {
            addCriterion("off_shelf >=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThan(Integer value) {
            addCriterion("off_shelf <", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThanOrEqualTo(Integer value) {
            addCriterion("off_shelf <=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfIn(List<Integer> values) {
            addCriterion("off_shelf in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotIn(List<Integer> values) {
            addCriterion("off_shelf not in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfBetween(Integer value1, Integer value2) {
            addCriterion("off_shelf between", value1, value2, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotBetween(Integer value1, Integer value2) {
            addCriterion("off_shelf not between", value1, value2, "offShelf");
            return (Criteria) this;
        }

        public Criteria andHotDegreeIsNull() {
            addCriterion("hot_degree is null");
            return (Criteria) this;
        }

        public Criteria andHotDegreeIsNotNull() {
            addCriterion("hot_degree is not null");
            return (Criteria) this;
        }

        public Criteria andHotDegreeEqualTo(Integer value) {
            addCriterion("hot_degree =", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeNotEqualTo(Integer value) {
            addCriterion("hot_degree <>", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeGreaterThan(Integer value) {
            addCriterion("hot_degree >", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_degree >=", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeLessThan(Integer value) {
            addCriterion("hot_degree <", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeLessThanOrEqualTo(Integer value) {
            addCriterion("hot_degree <=", value, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeIn(List<Integer> values) {
            addCriterion("hot_degree in", values, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeNotIn(List<Integer> values) {
            addCriterion("hot_degree not in", values, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeBetween(Integer value1, Integer value2) {
            addCriterion("hot_degree between", value1, value2, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andHotDegreeNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_degree not between", value1, value2, "hotDegree");
            return (Criteria) this;
        }

        public Criteria andViewNumIsNull() {
            addCriterion("view_num is null");
            return (Criteria) this;
        }

        public Criteria andViewNumIsNotNull() {
            addCriterion("view_num is not null");
            return (Criteria) this;
        }

        public Criteria andViewNumEqualTo(Long value) {
            addCriterion("view_num =", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotEqualTo(Long value) {
            addCriterion("view_num <>", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumGreaterThan(Long value) {
            addCriterion("view_num >", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumGreaterThanOrEqualTo(Long value) {
            addCriterion("view_num >=", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumLessThan(Long value) {
            addCriterion("view_num <", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumLessThanOrEqualTo(Long value) {
            addCriterion("view_num <=", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumIn(List<Long> values) {
            addCriterion("view_num in", values, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotIn(List<Long> values) {
            addCriterion("view_num not in", values, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumBetween(Long value1, Long value2) {
            addCriterion("view_num between", value1, value2, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotBetween(Long value1, Long value2) {
            addCriterion("view_num not between", value1, value2, "viewNum");
            return (Criteria) this;
        }

        public Criteria andPlayUrlIsNull() {
            addCriterion("play_url is null");
            return (Criteria) this;
        }

        public Criteria andPlayUrlIsNotNull() {
            addCriterion("play_url is not null");
            return (Criteria) this;
        }

        public Criteria andPlayUrlEqualTo(String value) {
            addCriterion("play_url =", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlNotEqualTo(String value) {
            addCriterion("play_url <>", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlGreaterThan(String value) {
            addCriterion("play_url >", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("play_url >=", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlLessThan(String value) {
            addCriterion("play_url <", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlLessThanOrEqualTo(String value) {
            addCriterion("play_url <=", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlLike(String value) {
            addCriterion("play_url like", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlNotLike(String value) {
            addCriterion("play_url not like", value, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlIn(List<String> values) {
            addCriterion("play_url in", values, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlNotIn(List<String> values) {
            addCriterion("play_url not in", values, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlBetween(String value1, String value2) {
            addCriterion("play_url between", value1, value2, "playUrl");
            return (Criteria) this;
        }

        public Criteria andPlayUrlNotBetween(String value1, String value2) {
            addCriterion("play_url not between", value1, value2, "playUrl");
            return (Criteria) this;
        }

        public Criteria andExtend2IsNull() {
            addCriterion("extend2 is null");
            return (Criteria) this;
        }

        public Criteria andExtend2IsNotNull() {
            addCriterion("extend2 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend2EqualTo(String value) {
            addCriterion("extend2 =", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotEqualTo(String value) {
            addCriterion("extend2 <>", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2GreaterThan(String value) {
            addCriterion("extend2 >", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2GreaterThanOrEqualTo(String value) {
            addCriterion("extend2 >=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2LessThan(String value) {
            addCriterion("extend2 <", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2LessThanOrEqualTo(String value) {
            addCriterion("extend2 <=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2Like(String value) {
            addCriterion("extend2 like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotLike(String value) {
            addCriterion("extend2 not like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2In(List<String> values) {
            addCriterion("extend2 in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotIn(List<String> values) {
            addCriterion("extend2 not in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2Between(String value1, String value2) {
            addCriterion("extend2 between", value1, value2, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotBetween(String value1, String value2) {
            addCriterion("extend2 not between", value1, value2, "extend2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
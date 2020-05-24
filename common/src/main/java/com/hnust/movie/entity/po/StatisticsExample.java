package com.hnust.movie.entity.po;

import java.util.ArrayList;
import java.util.List;

public class StatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StatisticsExample() {
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

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Long value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Long value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Long value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Long value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Long value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Long value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Long> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Long> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Long value1, Long value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Long value1, Long value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
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

        public Criteria andRatingNumIsNull() {
            addCriterion("rating_num is null");
            return (Criteria) this;
        }

        public Criteria andRatingNumIsNotNull() {
            addCriterion("rating_num is not null");
            return (Criteria) this;
        }

        public Criteria andRatingNumEqualTo(Long value) {
            addCriterion("rating_num =", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotEqualTo(Long value) {
            addCriterion("rating_num <>", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumGreaterThan(Long value) {
            addCriterion("rating_num >", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumGreaterThanOrEqualTo(Long value) {
            addCriterion("rating_num >=", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumLessThan(Long value) {
            addCriterion("rating_num <", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumLessThanOrEqualTo(Long value) {
            addCriterion("rating_num <=", value, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumIn(List<Long> values) {
            addCriterion("rating_num in", values, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotIn(List<Long> values) {
            addCriterion("rating_num not in", values, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumBetween(Long value1, Long value2) {
            addCriterion("rating_num between", value1, value2, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andRatingNumNotBetween(Long value1, Long value2) {
            addCriterion("rating_num not between", value1, value2, "ratingNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNull() {
            addCriterion("collect_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNotNull() {
            addCriterion("collect_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNumEqualTo(Long value) {
            addCriterion("collect_num =", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotEqualTo(Long value) {
            addCriterion("collect_num <>", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThan(Long value) {
            addCriterion("collect_num >", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThanOrEqualTo(Long value) {
            addCriterion("collect_num >=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThan(Long value) {
            addCriterion("collect_num <", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThanOrEqualTo(Long value) {
            addCriterion("collect_num <=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumIn(List<Long> values) {
            addCriterion("collect_num in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotIn(List<Long> values) {
            addCriterion("collect_num not in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumBetween(Long value1, Long value2) {
            addCriterion("collect_num between", value1, value2, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotBetween(Long value1, Long value2) {
            addCriterion("collect_num not between", value1, value2, "collectNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumIsNull() {
            addCriterion("play_num is null");
            return (Criteria) this;
        }

        public Criteria andPlayNumIsNotNull() {
            addCriterion("play_num is not null");
            return (Criteria) this;
        }

        public Criteria andPlayNumEqualTo(Long value) {
            addCriterion("play_num =", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumNotEqualTo(Long value) {
            addCriterion("play_num <>", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumGreaterThan(Long value) {
            addCriterion("play_num >", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumGreaterThanOrEqualTo(Long value) {
            addCriterion("play_num >=", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumLessThan(Long value) {
            addCriterion("play_num <", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumLessThanOrEqualTo(Long value) {
            addCriterion("play_num <=", value, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumIn(List<Long> values) {
            addCriterion("play_num in", values, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumNotIn(List<Long> values) {
            addCriterion("play_num not in", values, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumBetween(Long value1, Long value2) {
            addCriterion("play_num between", value1, value2, "playNum");
            return (Criteria) this;
        }

        public Criteria andPlayNumNotBetween(Long value1, Long value2) {
            addCriterion("play_num not between", value1, value2, "playNum");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
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
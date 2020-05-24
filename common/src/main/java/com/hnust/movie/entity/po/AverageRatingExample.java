package com.hnust.movie.entity.po;

import java.util.ArrayList;
import java.util.List;


public class AverageRatingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AverageRatingExample() {
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

        public Criteria andAvgIdIsNull() {
            addCriterion("avg_id is null");
            return (Criteria) this;
        }

        public Criteria andAvgIdIsNotNull() {
            addCriterion("avg_id is not null");
            return (Criteria) this;
        }

        public Criteria andAvgIdEqualTo(Long value) {
            addCriterion("avg_id =", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdNotEqualTo(Long value) {
            addCriterion("avg_id <>", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdGreaterThan(Long value) {
            addCriterion("avg_id >", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_id >=", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdLessThan(Long value) {
            addCriterion("avg_id <", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdLessThanOrEqualTo(Long value) {
            addCriterion("avg_id <=", value, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdIn(List<Long> values) {
            addCriterion("avg_id in", values, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdNotIn(List<Long> values) {
            addCriterion("avg_id not in", values, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdBetween(Long value1, Long value2) {
            addCriterion("avg_id between", value1, value2, "avgId");
            return (Criteria) this;
        }

        public Criteria andAvgIdNotBetween(Long value1, Long value2) {
            addCriterion("avg_id not between", value1, value2, "avgId");
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

        public Criteria andAverageRatingIsNull() {
            addCriterion("average_rating is null");
            return (Criteria) this;
        }

        public Criteria andAverageRatingIsNotNull() {
            addCriterion("average_rating is not null");
            return (Criteria) this;
        }

        public Criteria andAverageRatingEqualTo(String value) {
            addCriterion("average_rating =", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingNotEqualTo(String value) {
            addCriterion("average_rating <>", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingGreaterThan(String value) {
            addCriterion("average_rating >", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingGreaterThanOrEqualTo(String value) {
            addCriterion("average_rating >=", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingLessThan(String value) {
            addCriterion("average_rating <", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingLessThanOrEqualTo(String value) {
            addCriterion("average_rating <=", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingLike(String value) {
            addCriterion("average_rating like", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingNotLike(String value) {
            addCriterion("average_rating not like", value, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingIn(List<String> values) {
            addCriterion("average_rating in", values, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingNotIn(List<String> values) {
            addCriterion("average_rating not in", values, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingBetween(String value1, String value2) {
            addCriterion("average_rating between", value1, value2, "averageRating");
            return (Criteria) this;
        }

        public Criteria andAverageRatingNotBetween(String value1, String value2) {
            addCriterion("average_rating not between", value1, value2, "averageRating");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
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
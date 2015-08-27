define(['jquery', 'backbone'], function($, Backbone) {
    var Router = Backbone.Router.extend({
        routers: {
        	'rizu/city(:city)?ptType=(:ptType)&date=(:date)': 'requestList'
        },

        requestList: function(city, ptType, date) {
        	console.log(city);
        	console.log(ptType);
        	console.log(date);

        	/*
        	 1. search组件依赖数据变动 -》 search组件初始化值
        	 2. 请求列表数据 --> 列表渲染
        	*/
        }
    });

    return Router;
});
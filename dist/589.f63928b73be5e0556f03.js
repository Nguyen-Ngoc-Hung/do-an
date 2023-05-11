"use strict";(self.webpackChunkremote5=self.webpackChunkremote5||[]).push([[589],{45589:(p,i,d)=>{d.r(i),d.d(i,{ChartJSModule:()=>c});var h=d(31643),s=d(62943),e=d(5676);const m=[{path:"",component:(()=>{class r{constructor(){this.lineChartData=[{data:[65,59,80,81,56,55,40],label:"Series A"},{data:[28,48,40,19,86,27,90],label:"Series B"},{data:[18,48,77,9,100,27,40],label:"Series C"}],this.lineChartLabels=["January","February","March","April","May","June","July"],this.lineChartOptions={animation:!1,responsive:!0},this.lineChartColours=[{backgroundColor:"rgba(148,159,177,0.2)",borderColor:"rgba(148,159,177,1)",pointBackgroundColor:"rgba(148,159,177,1)",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"rgba(148,159,177,0.8)"},{backgroundColor:"rgba(77,83,96,0.2)",borderColor:"rgba(77,83,96,1)",pointBackgroundColor:"rgba(77,83,96,1)",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"rgba(77,83,96,1)"},{backgroundColor:"rgba(148,159,177,0.2)",borderColor:"rgba(148,159,177,1)",pointBackgroundColor:"rgba(148,159,177,1)",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"rgba(148,159,177,0.8)"}],this.lineChartLegend=!0,this.lineChartType="line",this.barChartOptions={scaleShowVerticalLines:!1,responsive:!0},this.barChartLabels=["2006","2007","2008","2009","2010","2011","2012"],this.barChartType="bar",this.barChartLegend=!0,this.barChartData=[{data:[65,59,80,81,56,55,40],label:"Series A"},{data:[28,48,40,19,86,27,90],label:"Series B"}],this.doughnutChartLabels=["Download Sales","In-Store Sales","Mail-Order Sales"],this.doughnutChartData=[350,450,100],this.doughnutChartType="doughnut",this.radarChartLabels=["Eating","Drinking","Sleeping","Designing","Coding","Cycling","Running"],this.radarChartData=[{data:[65,59,90,81,56,55,40],label:"Series A"},{data:[28,48,40,19,96,27,100],label:"Series B"}],this.radarChartType="radar",this.pieChartLabels=["Download Sales","In-Store Sales","Mail Sales"],this.pieChartData=[300,500,100],this.pieChartType="pie",this.polarAreaChartLabels=["Download Sales","In-Store Sales","Mail Sales","Telesales","Corporate Sales"],this.polarAreaChartData=[300,500,100,40,120],this.polarAreaLegend=!0,this.polarAreaChartType="polarArea"}chartClicked(n){console.log(n)}chartHovered(n){console.log(n)}}return r.\u0275fac=function(n){return new(n||r)},r.\u0275cmp=e.\u0275\u0275defineComponent({type:r,selectors:[["ng-component"]],decls:62,vars:24,consts:[[1,"animated","fadeIn"],[1,"card-columns","cols-2"],[1,"card"],[1,"card-header"],[1,"card-header-actions"],["href","http://www.chartjs.org"],[1,"text-muted"],[1,"card-body"],[1,"chart-wrapper"],["baseChart","",1,"chart",3,"datasets","labels","options","colors","legend","chartType","chartHover","chartClick"],["baseChart","",1,"chart",3,"datasets","labels","options","legend","chartType","chartHover","chartClick"],["baseChart","",1,"chart",3,"data","labels","chartType","chartHover","chartClick"],["baseChart","",1,"chart",3,"datasets","labels","chartType","chartHover","chartClick"],["baseChart","",1,"chart",3,"data","labels","legend","chartType","chartHover","chartClick"]],template:function(n,t){1&n&&(e.\u0275\u0275elementStart(0,"div",0),e.\u0275\u0275elementStart(1,"div",1),e.\u0275\u0275elementStart(2,"div",2),e.\u0275\u0275elementStart(3,"div",3),e.\u0275\u0275text(4," Line Chart "),e.\u0275\u0275elementStart(5,"div",4),e.\u0275\u0275elementStart(6,"a",5),e.\u0275\u0275elementStart(7,"small",6),e.\u0275\u0275text(8,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(9,"div",7),e.\u0275\u0275elementStart(10,"div",8),e.\u0275\u0275elementStart(11,"canvas",9),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(12,"div",2),e.\u0275\u0275elementStart(13,"div",3),e.\u0275\u0275text(14," Bar Chart "),e.\u0275\u0275elementStart(15,"div",4),e.\u0275\u0275elementStart(16,"a",5),e.\u0275\u0275elementStart(17,"small",6),e.\u0275\u0275text(18,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(19,"div",7),e.\u0275\u0275elementStart(20,"div",8),e.\u0275\u0275elementStart(21,"canvas",10),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(22,"div",2),e.\u0275\u0275elementStart(23,"div",3),e.\u0275\u0275text(24," Doughnut Chart "),e.\u0275\u0275elementStart(25,"div",4),e.\u0275\u0275elementStart(26,"a",5),e.\u0275\u0275elementStart(27,"small",6),e.\u0275\u0275text(28,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(29,"div",7),e.\u0275\u0275elementStart(30,"div",8),e.\u0275\u0275elementStart(31,"canvas",11),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(32,"div",2),e.\u0275\u0275elementStart(33,"div",3),e.\u0275\u0275text(34," Radar Chart "),e.\u0275\u0275elementStart(35,"div",4),e.\u0275\u0275elementStart(36,"a",5),e.\u0275\u0275elementStart(37,"small",6),e.\u0275\u0275text(38,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(39,"div",7),e.\u0275\u0275elementStart(40,"div",8),e.\u0275\u0275elementStart(41,"canvas",12),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(42,"div",2),e.\u0275\u0275elementStart(43,"div",3),e.\u0275\u0275text(44," Pie Chart "),e.\u0275\u0275elementStart(45,"div",4),e.\u0275\u0275elementStart(46,"a",5),e.\u0275\u0275elementStart(47,"small",6),e.\u0275\u0275text(48,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(49,"div",7),e.\u0275\u0275elementStart(50,"div",8),e.\u0275\u0275elementStart(51,"canvas",11),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(52,"div",2),e.\u0275\u0275elementStart(53,"div",3),e.\u0275\u0275text(54," Polar Area Chart "),e.\u0275\u0275elementStart(55,"div",4),e.\u0275\u0275elementStart(56,"a",5),e.\u0275\u0275elementStart(57,"small",6),e.\u0275\u0275text(58,"docs"),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementStart(59,"div",7),e.\u0275\u0275elementStart(60,"div",8),e.\u0275\u0275elementStart(61,"canvas",13),e.\u0275\u0275listener("chartHover",function(a){return t.chartHovered(a)})("chartClick",function(a){return t.chartClicked(a)}),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd(),e.\u0275\u0275elementEnd()),2&n&&(e.\u0275\u0275advance(11),e.\u0275\u0275property("datasets",t.lineChartData)("labels",t.lineChartLabels)("options",t.lineChartOptions)("colors",t.lineChartColours)("legend",t.lineChartLegend)("chartType",t.lineChartType),e.\u0275\u0275advance(10),e.\u0275\u0275property("datasets",t.barChartData)("labels",t.barChartLabels)("options",t.barChartOptions)("legend",t.barChartLegend)("chartType",t.barChartType),e.\u0275\u0275advance(10),e.\u0275\u0275property("data",t.doughnutChartData)("labels",t.doughnutChartLabels)("chartType",t.doughnutChartType),e.\u0275\u0275advance(10),e.\u0275\u0275property("datasets",t.radarChartData)("labels",t.radarChartLabels)("chartType",t.radarChartType),e.\u0275\u0275advance(10),e.\u0275\u0275property("data",t.pieChartData)("labels",t.pieChartLabels)("chartType",t.pieChartType),e.\u0275\u0275advance(10),e.\u0275\u0275property("data",t.polarAreaChartData)("labels",t.polarAreaChartLabels)("legend",t.polarAreaLegend)("chartType",t.polarAreaChartType))},directives:[h.jh],encapsulation:2}),r})(),data:{title:"Charts"}}];let C=(()=>{class r{}return r.\u0275fac=function(n){return new(n||r)},r.\u0275mod=e.\u0275\u0275defineNgModule({type:r}),r.\u0275inj=e.\u0275\u0275defineInjector({imports:[[s.RouterModule.forChild(m)],s.RouterModule]}),r})(),c=(()=>{class r{}return r.\u0275fac=function(n){return new(n||r)},r.\u0275mod=e.\u0275\u0275defineNgModule({type:r}),r.\u0275inj=e.\u0275\u0275defineInjector({imports:[[C,h.m9]]}),r})()}}]);
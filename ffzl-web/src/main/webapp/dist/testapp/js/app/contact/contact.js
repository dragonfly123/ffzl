/*! ffzl 2016-05-16 */
app.controller("ContactCtrl",["$scope","$http","$filter",function(a,b,c){b.get("js/app/contact/contacts.json").then(function(b){a.items=b.data.items,a.item=c("orderBy")(a.items,"first")[0],a.item.selected=!0}),a.filter="",a.groups=[{name:"Coworkers"},{name:"Family"},{name:"Friends"},{name:"Partners"},{name:"Group"}],a.createGroup=function(){var b={name:"New Group"};b.name=a.checkItem(b,a.groups,"name"),a.groups.push(b)},a.checkItem=function(a,b,c){var d=0;return angular.forEach(b,function(b){if(0==b[c].indexOf(a[c])){var e=b[c].replace(a[c],"").trim();d=e?Math.max(d,parseInt(e)+1):1}}),a[c]+(d?" "+d:"")},a.deleteGroup=function(b){a.groups.splice(a.groups.indexOf(b),1)},a.selectGroup=function(b){angular.forEach(a.groups,function(a){a.selected=!1}),a.group=b,a.group.selected=!0,a.filter=b.name},a.selectItem=function(b){angular.forEach(a.items,function(a){a.selected=!1,a.editing=!1}),a.item=b,a.item.selected=!0},a.deleteItem=function(b){a.items.splice(a.items.indexOf(b),1),a.item=c("orderBy")(a.items,"first")[0],a.item&&(a.item.selected=!0)},a.createItem=function(){var b={group:"Friends",avatar:"img/a0.jpg"};a.items.push(b),a.selectItem(b),a.item.editing=!0},a.editItem=function(a){a&&a.selected&&(a.editing=!0)},a.doneEditing=function(a){a.editing=!1}}]);
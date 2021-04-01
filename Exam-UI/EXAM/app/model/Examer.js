Ext.define('Desktop.model.Examer', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'examerKey'},
		{name : 'name'},
		{name : 'email'},
		{name : 'phone'},
		{name : 'organization'},
		{name : 'designation'},
		{name : 'dateOfBirth'},
		{name : 'role'},
	]
});

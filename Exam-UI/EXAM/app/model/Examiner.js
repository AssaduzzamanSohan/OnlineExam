Ext.define('Desktop.model.Examiner', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'examinerKey'},
		{name : 'name'},
		{name : 'email'},
		{name : 'phone'},
		{name : 'organization'},
		{name : 'designation'},
		{name : 'allowedToPrepareExam'},
		{name : 'role'},
	]
});

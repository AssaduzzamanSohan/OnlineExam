Ext.define('Desktop.model.Question', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'questionKey'},
		{name : 'question'},
		{name : 'mark'},
	]
});

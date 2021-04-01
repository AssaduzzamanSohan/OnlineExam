Ext.define('Desktop.model.Option', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'optionKey'},
		{name : 'questionKey'},
		{name : 'option'},
		{name : 'correctAns'},
	]
});

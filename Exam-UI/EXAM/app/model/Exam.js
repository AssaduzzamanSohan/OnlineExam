Ext.define('Desktop.model.Exam', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'examKey'},
		{name : 'examType'},
		{name : 'examinerKey'},
		{name : 'totalTimeInMin'},
		{name : 'negativeMark'},
		{name : 'doNegativeMarking'},
		{name : 'useEachQusTime'},
	]
});

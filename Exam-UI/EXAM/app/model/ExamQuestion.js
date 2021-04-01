Ext.define('Desktop.model.ExamQuestion', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'examQusKey'},
		{name : 'examKey'},
		{name : 'questionKey'},
		{name : 'question'},
		{name : 'answerOptionKey'},
		{name : 'answer'},
		{name : 'mark'},
		{name : 'timeInSecond'},
	]
});

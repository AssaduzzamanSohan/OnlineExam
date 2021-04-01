Ext.define('Desktop.model.ExamAnswer', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'examAnsKey'},
		{name : 'examResultKey'},
		{name : 'examerKey'},
		{name : 'examQusKey'},
		{name : 'correctAnswer'},
		{name : 'givenAnswerKey'},
		{name : 'answerIsCorrect'},
		{name : 'mark'},
		{name : 'acquiredMark'},
		{name : 'dtt_answer_time'},
	]
});

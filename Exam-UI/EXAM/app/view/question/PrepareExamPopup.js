Ext.define('Desktop.view.question.PrepareExamPopup', {
    extend : 'Ext.panel.Panel',
    title : "Prepare Exam",
    requires: [
        'Ext.tab.Panel',
        'Ext.tab.Tab',
        'Ext.grid.Panel',
        'Ext.grid.column.Boolean',
        'Ext.toolbar.Toolbar',
        'Ext.form.field.Text',
        'Ext.tree.Panel',
        'Ext.tree.View',
        'Ext.grid.filters.filter.Number',
        'Ext.grid.column.Date',
        'Ext.grid.filters.filter.Date',
        'Ext.grid.filters.filter.String',
        'Ext.grid.filters.filter.Boolean',
        'Ext.selection.CheckboxModel',
        'Ext.grid.filters.Filters',
        'Ext.grid.feature.Summary',
    ],

    controller: 'questionController',

    closable : true,
    floatable : true,
    floating : true,
    draggable : true,
    width : 600,
    height :600,
    modal : true,
    layout: 'fit',

    itemId: 'prepareExamPopup',

    items : [
    {
        xtype : "panel",
        itemId : 'cadReportPopupPanel',
        reference: 'cadReportPopupPanel',
        width : 580,
        border:false,
        layout: 'column',
        items:[
            {
                xtype: 'textfield',
                itemId: 'examTitle',
                reference: 'examTitle',
                fieldLabel: 'Title',
                labelAlign: 'left',
                labelWidth: 200,
                columnWidth: 1,
                margin: '10 10 0 10',
            },
            {
                xtype: 'numberfield',
                itemId: 'totalTimeInMin',
                reference: 'totalTimeInMin',
                fieldLabel: 'Total Time(Minute)',
                labelAlign: 'left',
                labelWidth: 200,
                margin: '10 10 0 10',
                columnWidth: 1,
                format: '0.00',
                hideTrigger: true,
                keyNavEnabled: false,
                mouseWheelEnabled: false
            },
            {
                xtype: 'checkboxfield',
                boxLabel: '',
                reference:'negativeMark',
                margin: '10 10 0 10',
                name: 'topping',
                inputValue: '1',
                columnWidth: .05
            },            
            {
                xtype: 'numberfield',
                itemId: 'negativeMarkValue',
                reference: 'negativeMarkValue',
                fieldLabel: 'Negative Mark Value',
                labelAlign: 'right',
                labelWidth: 200,
                margin: '10 10 0 10',
                columnWidth: .95,
                format: '0.00',
                hideTrigger: true,
                keyNavEnabled: false,
                mouseWheelEnabled: false
            },
            {
                xtype: 'checkboxfield',
                boxLabel: 'Use Each Qus Time',
                reference:'useEachQusTime',
                name: 'topping',
                margin: '10 10 0 10',
                inputValue: '1',
                labelWidth: 200,
                columnWidth: 1
            }
        ]
    }],
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'bottom',
        itemId:'btnPanel',
        items : ['->',
            {
                itemId  : 'savePrepareExam',
                reference : 'savePrepareExam',
                text    : 'Save',
                width : 80,
                hidden  :  false,
                listeners : {
                   click : 'onSavePrepareExam'
                }
            },
        ]
    }],

    listeners: {
        afterrender: 'onActivatePrepareExamPopup'
    }
});
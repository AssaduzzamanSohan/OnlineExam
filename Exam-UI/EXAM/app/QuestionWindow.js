/*!
 * Ext JS Library
 * Copyright(c) 2006-2014 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Desktop.QuestionWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'question-win',

    init : function(){
        this.launcher = {
            text: 'question',
            iconCls:'icon-question-grid'
        };
        this.createGlobalStores();
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('question-win');
        if(loginUser == null ||  loginUser == '') {
            loginUser=gLoginUuser;
        }
        else{
            loginUser=loginUser;
        }
        if(!win){
            win = desktop.createWindow({
                id: 'question-win',
                title:'question - ' + loginUser.firstName + ' ' + loginUser.lastName,
                width: desktop.getWidth() - 200,
                height: desktop.getHeight() - 50,
                iconCls: 'icon-question-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        xtype: 'questionPanel'
                    }
                ]
            });
        }
        return win;
    },

    createGlobalStores : function(){

        Ext.create('Ext.data.Store', {
            model: 'Desktop.model.Question',
            storeId: 'gQuestionStore'
        });
        Ext.create('Ext.data.Store', {
            model: 'Desktop.model.Option',
            storeId: 'gOptionStoreToSave'
        });
        Ext.create('Ext.data.Store', {
            model: 'Desktop.model.Option',
            storeId: 'gOptionStore'
        });
        Ext.create('Ext.data.Store', {
            model: 'Desktop.model.ExamQuestion',
            storeId: 'gExamQuestionStore'
        });
    }
});


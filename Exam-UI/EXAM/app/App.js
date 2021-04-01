/*!
 * Ext JS Library
 * Copyright(c) 2006-2014 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */


Ext.define('Desktop.App', {
    extend: 'Ext.ux.desktop.App',

    name: 'EXAM',

    requires: [
        'Ext.window.MessageBox',
        'Ext.ux.desktop.ShortcutModel'
    ],

    init: function(start) {
        this.callParent();
    },

    getModules : function() {

       var modules = new Array();

       if(loginUser) {
            if(gRole == 'EXAMINER'){
                modules.push(new Desktop.QuestionWindow());
            }
            if(gRole == 'EXAMER'){
                modules.push(new Desktop.ExamWindow());
            }
        }
        return modules;
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        /*
         * Added a shortcut store
         * According to permission icons are added to the store.
         */

        var shortcutIcons = Ext.create('Ext.data.Store', {
            model: 'Ext.ux.desktop.ShortcutModel'
        });

      if(loginUser) {
        
            if(gRole == 'EXAMINER'){
                shortcutIcons.add({ name: 'Question Bank', iconCls: 'question-bank-shortcut', module: 'question-win' });
            }
            if(gRole == 'EXAMER'){
               shortcutIcons.add({ name: 'Exam', iconCls: 'exam-shortcut', module: 'exam-win' });
            }
        }

        return Ext.apply(ret, {
            shortcuts: shortcutIcons,

            wallpaper: 'resources/images/wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
    },

    getTaskbarConfig: function () {
    }
});




/*global jQuery:true, intershop:true */
'use strict';
!function (global, utils, $) {
    
    var ui = utils.namespace('intershop.propertygroups.ui'),
        editor = utils.namespace('intershop.propertygroups.ui.editor'),
        template = '<div class="propertygroup-editor propertygroup-editor-luckynumber">' + 
                       '<input class="propertygroup-editor-luckynumber-value" type="hidden"/>' +
                   '</div>',
        viewTemplate = '<div class="propertygroup-editor-luckynumber-value-view"></div>';
     
    /**
     * Create a new RadioGroup editor.
     * @param {Object} json configuration as JSON
     * @param {String} name name of the editor
     */
    var LuckyNumber = function LuckyNumber(json, name) {
        
        this.$el = $($.parseHTML(template));
               
        Object.defineProperty(this, 'luckyNumber', 
                {
                    configurable: true,
                    enumerable: true,
                    get: function () {
                        return this.$el.find('.propertygroup-editor-luckynumber-value').prop( "value" );
                    },
                    set: function (v) {
                        var $checkBox = this.$el.find('.propertygroup-editor-luckynumber-value');
                        $checkBox.prop('value', v);
                    }
        });
        
        this.fromJSON(json);
        
        this.bindEvents();
    };
    
    LuckyNumber.prototype.bindEvents = function bindEvents() {
        var _this = this;
        this.$el.find('.propertygroup-editor-luckynumber-value')
            .on('change.propertygroup', function onChange() {
                _this.modified = true;
            });
    };
    
    LuckyNumber.prototype.fromJSON = function fromJSON(originJSON) {
        
        var json = $.extend(true, {}, originJSON);
        this.modified = json.modified || false;
        if (json.luckyNumber)
        {
            this.luckyNumber = json.luckyNumber;
        }
    };
    
    /**
     * Create configuration as JSON.
     * @param {Object} originJSON old configuration as JSON
     * @return {Object} updated configuration as JSON
     */
    LuckyNumber.prototype.toJSON = function toJSON(originJSON) {
        var json = $.extend(true, {}, originJSON);
        
        json.modified = this.modified;
        if (this.modified) {
            json.luckyNumber = this.luckyNumber;
        }
        
        return json;
    };
    
    /**
     * Enable the editor.
     */
    LuckyNumber.prototype.enable = function enable() {
        this.$el.find('.propertygroup-editor-luckynumber-value').prop('disabled', false);
    };

    /**
     * Disable the editor.
     */
    LuckyNumber.prototype.disable = function disable() {
        this.$el.find('.propertygroup-editor-luckynumber-value').prop('disabled', true);
    };
    
    /**
     * View the value of the editor.
     */
    LuckyNumber.prototype.view = function view() {
        if (this.luckyNumber != '') {
            this.$el.text(this.luckyNumber);
        }
        else {
            this.$el.html('&nbsp;');   
        }
    };
    
    LuckyNumber.prototype.render = function render() {
        return this.$el;
    };
    
    editor.LuckyNumber = editor.LuckyNumber || LuckyNumber;
    ui.Controller.registerEditor('com.intershop.adapter.payment.partnerpay.capi.propgroups.ui.LuckyNumberUIEditor', LuckyNumber);
    
}(window, intershop.propertygroups.utils, jQuery);

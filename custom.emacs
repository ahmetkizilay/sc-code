
;inserts basic function template
(fset 'sc-play-template
   (lambda (&optional arg) "Keyboard macro." (interactive "p") (kmacro-exec-ring-item (quote ("({/.play;	" 0 "%d")) arg)))

; inserts initial code I use frequently
(fset 'sc-init-code
   (lambda (&optional arg) "Keyboard macro." (interactive "p") (kmacro-exec-ring-item (quote ("Server.default = s = Server.local;s.boot;s.quit;SwingOSC.default.boot;SwingOSC.default.quit;Help.gui;FreqScope.new(500, 200l;" 0 "%d")) arg)))

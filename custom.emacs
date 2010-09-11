; my emacs defitions file for SuperCollider
; var name = "ahmet", surname = "kizilay";
; var email = name + "." + surname + "@" + "gmail.com";

; you should add a reference to your .emacs file 
; (load-file "~/share/SuperCollider/Extensions/custom.emacs")

; command: M-x sc-play-template
; description: inserts basic function template
; output:
; (
;  {
;
;  }.play;
; )
;

(fset 'sc-play-template
   (lambda (&optional arg) "Keyboard macro." (interactive "p") (kmacro-exec-ring-item (quote ("({/.play;	" 0 "%d")) arg)))

; command: M-x sc-init-code
; description: inserts initial code I use frequently
; output:
; Server.default = s = Server.local;
; s.boot;
; s.quit;
;
; SwingOSC.default.boot;
; SwingOSC.default.quit;
;
; Help.gui;
;
; FreqScope.new(500, 200);

(fset 'sc-init-code
   (lambda (&optional arg) "Keyboard macro." (interactive "p") (kmacro-exec-ring-item (quote ("Server.default = s = Server.local;s.boot;s.quit;SwingOSC.default.boot;SwingOSC.default.quit;Help.gui;FreqScope.new(500, 200l;" 0 "%d")) arg)))

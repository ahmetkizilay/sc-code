; my emacs defitions file for SuperCollider
; var name = "ahmet", surname = "kizilay";
; var email = name + "." + surname + "@" + "gmail.com";

; you should add a reference to your .emacs file 
; (load-file "~/share/SuperCollider/Extensions/custom.emacs")
; if you get kmacro-exec-ring-item error, You should run: M-x load-library <enter> kmacro
; or add the following reference to your .emacs file
; (load-library "kmacro")

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


; command: M-x sc-synthdef-template
; description: inserts a basic synthdef template
; output:
; (
; SynthDef("", {
; 	arg out = 0;
; 	var sig;
; 
; 	Out.ar(out, sig);
; }).send(s);
; )

(fset 'sc-synthdef-template
   (lambda (&optional arg) "Keyboard macro." (interactive "p") (kmacro-exec-ring-item (quote ("(SynthDef(.esend(s;\", {	arg out = 0;	var sig;	Out.ar(out, sig;" 0 "%d")) arg)))

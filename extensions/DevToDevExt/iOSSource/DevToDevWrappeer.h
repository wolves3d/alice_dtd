@interface DevToDevWrappeer : NSObject
{
}

- (double) AddTwoNumbers:(double)arg0 Arg2:(double)arg1;
- (NSString *) BuildAString:(char *)arg0 Arg2:(char *)arg1;
- (NSString *) HowManyObjects:(double)arg0 Arg2:(double)arg1 Arg3:(char *)arg2;
- (void)ReturnAsync:(double)arg0 Arg2:(double)arg1;

- (double) staticInit;
- (double) staticFinal;
- (double) customEvent:(char *)arg0;
- (double) levelUp:(double)arg0;
- (double) tutorialCompleted:(double)arg0;

- (double) progressionEvent:(char *)locationName
	Arg2:(double) spentTime
	Arg3:(double) spentDot
	Arg4:(double) spentBomb
	Arg5:(double) spentPaint
	Arg6:(double) spentTurns
	Arg7:(double) spentMoreTurns
	Arg8:(double) spentOpenBoxes
	Arg9:(double) success
	Arg10:(double) difficulty
	Arg11:(double) playHour
	Arg12:(double) coinsSpentProgress
	Arg13:(double) coinsSpentFinal
	Arg14:(double) coinsBalance;

- (double) inAppPurchase:(char*)purchaseId
		Arg2:(char*)purchaseType
		Arg3:(double) purchaseAmount
		Arg4:(double) purchasePrice
		Arg5:(char*) purchaseCurrency;

- (double) realPayment:(char*)paymentId
		Arg2:(double) inAppPrice
		Arg3:(char*) inAppName
		Arg4:(char*) inAppCurrencyISOCode;
		
@end
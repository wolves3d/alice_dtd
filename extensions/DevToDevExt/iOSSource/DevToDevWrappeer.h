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

- (double) progressionEvent:(char *)locationName
	Arg2:(double) spentTime
	Arg3:(double) spentDot
	Arg4:(double) spentBomb
	Arg5:(double) spentPaint
	Arg6:(double) spentTurns
	Arg7:(double) spentMoreTurns
	Arg8:(double) spentOpenBoxes
	Arg9:(double) spentEP
	Arg10:(double) earnedCoins;

- (double) inAppPurchase:(char*)purchaseId
		Arg2:(char*)purchaseType
		Arg3:(double) purchaseAmount
		Arg4:(double) purchasePrice
		Arg5:(char*) purchaseCurrency;

@end